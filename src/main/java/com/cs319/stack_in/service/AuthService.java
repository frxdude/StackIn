package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.request.auth.ConfirmOTPRequest;
import com.cs319.stack_in.dto.request.auth.GenerateOTPRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * AuthService
 *
 * @author Sainjargal Ishdorj
 **/

public interface AuthService {

    AuthResponse login (AuthRequest authRequest, HttpServletRequest req) throws BusinessException;

    AuthResponse exchangeToken (String refreshToken, HttpServletRequest req) throws TokenException;

    HashMap<String, String> sendOtp(GenerateOTPRequest otpRequest, HttpServletRequest req) throws BusinessException, UnsupportedEncodingException;

    HashMap<String, String> confirmOTP(ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException;

}
