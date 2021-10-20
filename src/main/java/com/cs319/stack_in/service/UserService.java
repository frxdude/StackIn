package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * UserService
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserService {

    AuthResponse resetPassword (Long id, HttpServletRequest req) throws BusinessException;
}
