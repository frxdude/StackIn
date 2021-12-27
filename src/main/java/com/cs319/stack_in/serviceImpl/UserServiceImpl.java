package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.auth.AuthRegisterRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.entity.Role;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.ProfessionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.UserService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserServiceImpl implements UserService {

    PasswordEncoder encoder;
    UserRepository repository;
    JwtTokenProvider jwtTokenProvider;
    Localization localization;
    ProfessionRepository professionRepository;
    @Autowired
    public UserServiceImpl(PasswordEncoder encoder, UserRepository repository,
                           JwtTokenProvider jwtTokenProvider, Localization localization, ProfessionRepository professionRepository) {
        this.encoder = encoder;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.localization = localization;
        this.professionRepository = professionRepository;

    }

    /**
     * @param id  Long
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse resetPassword(Long id, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + "" + "]");
            Logger.info(this.getClass().getName(), "[login][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException when User already exists
     * @author Sainjargal Ishdorj
     **/

    public AuthResponse register(AuthRegisterRequest authRegisterRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[register][input][" + authRegisterRequest.toString() + "]");

            Optional<User> optionalUser = repository.findByUsername(authRegisterRequest.getUsername());

            if (optionalUser.isPresent())
                throw new BusinessException(localization.getMessage("user.already"), "User already exists");

            Profession profession = professionRepository.findById(authRegisterRequest.getProfessionID())
                    .orElseThrow(() -> new BusinessException(
                            localization.getMessage("profession.not.found"), "Profession not found"));

            User user = repository.save(User.builder()
                    .isActive(true)
                    .username(authRegisterRequest.getUsername())
                    .password(encoder.encode(authRegisterRequest.getPassword()))
                    .email(authRegisterRequest.getEmail())
                    .phone(authRegisterRequest.getPhone())
                    .profession(profession)
                    .roles(Collections.singletonList(Role.ROLE_USER))
                    .build());

            AuthResponse authResponse = AuthResponse.builder()
                    .accessToken(jwtTokenProvider.createToken(user.getId(), user.getRoles().get(0), true))
                    .refreshToken(jwtTokenProvider.createToken(user.getId(), user.getRoles().get(0), false))
                    .build();

            Logger.info(this.getClass().getName(), "[register][output][" + "" + "]");
            return authResponse;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[register][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[register][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return {@link User}
     * @throws BusinessException when User not found
     * @author Sainjargal Ishdorj
     **/

    public User findUser(HttpServletRequest req) throws BusinessException {
        try {
            return repository.findById(Long.valueOf(req.getRemoteUser()))
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[findUser][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[findUser][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
