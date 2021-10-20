package com.cs319.stack_in.jwt;

import com.cs319.stack_in.entity.Role;
import com.cs319.stack_in.exception.TokenException;
import com.cs319.stack_in.serviceImpl.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

/**
 * JwtTokenProvider
 *
 * @author Sainjargal Ishdorj
 **/


@Component
public class JwtTokenProvider {

    @Autowired
    Environment env;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private String secretKey;
    private final long validityInMilliseconds = 86400000; // 1 day
    private final BigInteger refreshValidityInMilliseconds = new BigInteger("31536000000"); // 1 year
    private final long limitedValidityInMilliseconds = 1800000; // 30 min

    @PostConstruct
    protected void init() {
        secretKey = env.getProperty("jwt.key");
    }

    public String createToken(String uniqueId, Role role, String family, boolean isAccess) {

        Claims claims = Jwts.claims().setSubject(uniqueId);
        claims.put("auth", role);
        if (StringUtils.isNotBlank(family)) claims.put("family", family);

        Date now = new Date();
        Date validity = new Date(now.getTime() + (isAccess ? validityInMilliseconds : refreshValidityInMilliseconds).intValue());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = getRole(token).equals("ROLE_TEMP") ? userDetailsService.loadUserByRoleTemp(getSubject(token)) : userDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth").toString();
    }

    public String getStatus(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("isPhone").toString();
    }

    public String getFamily(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("family") != null
                ? Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("family").toString()
                : "";
    }

    public String exchangeToken(String refreshToken) {
        String newAccessToken = createToken(
                getSubject(refreshToken),
                Role.valueOf(getRole(refreshToken)),
                getFamily(refreshToken) != null ? getFamily(refreshToken) : "",
                true);
        return newAccessToken;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws TokenException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }

}
