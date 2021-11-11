package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.request.auth.ConfirmOTPRequest;
import com.cs319.stack_in.dto.request.auth.GenerateOTPRequest;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;
import com.cs319.stack_in.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * AuthController
 *
 * @author Sainjargal Ishdorj
 **/

@Api(tags = "Auth")
@RestController
@RequestMapping("auth")
public class AuthController {

    AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> adminLogin(@Valid @RequestBody AuthRequest authRequest, HttpServletRequest req) throws
            BusinessException {
        return ResponseEntity.ok(service.login(authRequest, req));
    }

    @RequestMapping(value = "exchange", method = RequestMethod.GET)
    public ResponseEntity<Object> exchangeToken(@RequestParam String refreshToken, HttpServletRequest req) throws
            TokenException {
        return ResponseEntity.ok(service.exchangeToken(refreshToken, req));
    }

    @RequestMapping(value = "otp/send", method = RequestMethod.POST)
    public ResponseEntity<Object> sendOTP(@Valid @RequestBody GenerateOTPRequest otpRequest,
                                          HttpServletRequest req) throws BusinessException, UnsupportedEncodingException {
        return ResponseEntity.ok(service.sendOtp(otpRequest, req));
    }

    @RequestMapping(value = "otp/confirm", method = RequestMethod.POST)
    public ResponseEntity<Object> confirmOTP(@Valid @RequestBody ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.confirmOTP(otpRequest, req));
    }
}
