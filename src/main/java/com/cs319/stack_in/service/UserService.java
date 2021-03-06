package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.auth.AuthRegisterRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;

import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * UserService
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserService {

    AuthResponse resetPassword (Long id, HttpServletRequest req) throws BusinessException;

    AuthResponse register(AuthRegisterRequest authRegisterRequest, HttpServletRequest req) throws BusinessException;

    User findUser(HttpServletRequest req) throws BusinessException;
}
