package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.request.auth.ConfirmOTPRequest;
import com.cs319.stack_in.dto.request.auth.GenerateOTPRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Role;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.entity.redis.OneTimePassword;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.helper.MailThread;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.repository.redis.OTPRepository;
import com.cs319.stack_in.service.AuthService;
import com.cs319.stack_in.service.UserService;
import com.cs319.stack_in.util.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

/**
 * AuthServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    OTPRepository otpRepository;
    Localization localization;
    JwtTokenProvider jwtTokenProvider;
    PasswordEncoder encoder;
    UserService userService;
    AuthenticationManager authenticationManager;

    HashMap<String, String> response;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, OTPRepository otpRepository, Localization localization, JwtTokenProvider jwtTokenProvider, PasswordEncoder encoder, UserService userService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.localization = localization;
        this.jwtTokenProvider = jwtTokenProvider;
        this.encoder = encoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException when
     * @author Sainjargal Ishdorj
     **/

    public AuthResponse login(AuthRequest authRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + authRequest.toString() + "]");
            User user = userRepository.findByEmail(authRequest.getUsername())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found")));
            if (!encoder.matches(authRequest.getPassword(), user.getPassword()))
                throw new BusinessException(localization.getMessage("auth.username.pass.not.match"), "Username or Password doesnt match");

            if (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUniqueId(), authRequest.getPassword())).isAuthenticated()) {

                Role role = user.getRoles().get(0);
                AuthResponse authResponse = AuthResponse.builder()
                        .accessToken(jwtTokenProvider.createToken(user.getUniqueId(), role, true))
                        .refreshToken(jwtTokenProvider.createToken(user.getUniqueId(), role, false))
                        .build();

                Logger.info(getClass().getName(), "[login][output][" + authResponse.toString() + "]");
                return authResponse;
            } else
                throw new BusinessException(localization.getMessage("auth.username.pass.not.match"), "username or password doesnt match");
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws TokenException
     * @author Sainjargal Ishdorj
     **/

    public AuthResponse exchangeToken(String refreshToken, HttpServletRequest req) throws TokenException {
        try {
            Logger.info(this.getClass().getName(), "[exchangeToken][input][" + "" + "]");
            Logger.info(this.getClass().getName(), "[exchangeToken][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[exchangeToken][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }


    /**
     * @param otpRequest {@link GenerateOTPRequest} DTO
     * @param req        servlet request
     * @return HashMap response
     * @throws BusinessException thrown when user exists,
     * @author Sainjargal Ishdorj
     **/

    public HashMap<String, String> sendOtp(GenerateOTPRequest otpRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(getClass().getName(), "[sendOtp][input][" + otpRequest.toString() + "]");

            if (!otpRequest.getValue().matches("^(.+)@(.+)$"))
                throw new BusinessException(localization.getMessage("val.email"), "Invalid email");

            Optional<User> optionalUser = userRepository.findByUsername(otpRequest.getValue());

            if (optionalUser.isPresent() && optionalUser.get().isActive())
                throw new BusinessException(localization.getMessage("user.already"), "User already exists");

            String generatedOTP = RandomStringUtils.randomNumeric(4);
            HashMap<String, String> response = new HashMap<>();
            Optional<OneTimePassword> optionalOTP = otpRepository.findById(otpRequest.getValue());

            if (optionalOTP.isPresent()) {
                OneTimePassword otp = optionalOTP.get();
                throw new BusinessException(localization.getMessage("otp.time", new Object[]{otp.getTimeout()}), "Confirmation password is alive");
            } else {
                MailThread mailThread = new MailThread("<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
                        "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\n" +
                        "    <div style=\"border-bottom:1px solid #eee\">\n" +
                        "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">StackIn</a>\n" +
                        "    </div>\n" +
                        "    <p style=\"font-size:1.1em\">Сайн байна уу,</p>\n" +
                        "    <p>StackIn сонгосон танд баярлалаа. Энэхүү баталгаажуулах кодыг ашиглан цааш бүртгүүлнэ үү. Баталгаажуулах кодын хүчингүй болох хугацаа 5 минут</p>\n" +
                        "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + generatedOTP + "</h2>\n" +
                        "    <p style=\"font-size:0.9em;\">Хүндэтгэсэн,<br />StackIn </p>\n" +
                        "    <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
                        "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\n" +
                        "      <p>Програм хангамж 4-р курс</p>\n" +
                        "      <p>B180910040</p>\n" +
                        "      <p>И.Сайнжаргал</p>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</div>", otpRequest.getValue(), "StackIn batalgaajuulah shuudan");
                mailThread.start();

                otpRepository.save(new OneTimePassword(otpRequest.getValue(), generatedOTP, 3, (long) 300));
                response.put("message", localization.getMessage("otp.send", new Object[]{otpRequest.getValue(), generatedOTP}));
            }

            Logger.info(getClass().getName(), "[sendOtp][output][" + response.get("message") + "]");
            return response;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[sendOtp][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(getClass().getName(), "[sendOtp][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param otpRequest {@link ConfirmOTPRequest} DTO
     * @param req        servlet request
     * @return HashMap response
     * @throws BusinessException thrown when otp expired, tries exceeded, doesnt match
     * @author Sainjargal Ishdorj
     **/

    public HashMap<String, String> confirmOTP(ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(getClass().getName(), "[confirmOTP][input][" + otpRequest.toString() + "]");

            OneTimePassword oneTimePassword = otpRepository.findById(otpRequest.getValue())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("otp.expired"), "Confirm time expired"));

            if (oneTimePassword.getTries() <= 0)
                throw new BusinessException(localization.getMessage("otp.tries.exceeded"), "Tries exceeded");

            if (!oneTimePassword.getOtp().equals(otpRequest.getOtp())) {
                oneTimePassword.setTries(oneTimePassword.getTries() - 1);
                otpRepository.save(oneTimePassword);
                throw new BusinessException(localization.getMessage("otp.invalid"), "Invalid confirm password");
            }

            otpRepository.delete(oneTimePassword);

            response = new HashMap<>();
            response.put("accessToken", jwtTokenProvider.createTempToken(otpRequest.getValue()));

            Logger.info(getClass().getName(), "[confirmOTP][output][" + response + "]");
            return response;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[confirmOTP][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(getClass().getName(), "[confirmOTP][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
