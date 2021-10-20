package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthService
 *
 * @author Sainjargal Ishdorj
 **/

public interface AuthService {

    AuthResponse login (AuthRequest authRequest, HttpServletRequest req) throws BusinessException;

    AuthResponse register (HttpServletRequest req) throws BusinessException;

    AuthResponse exchangeToken (String refreshToken, HttpServletRequest req) throws TokenException;

}
