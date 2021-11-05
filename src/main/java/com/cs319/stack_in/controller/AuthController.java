package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.exception.TokenException;
import com.cs319.stack_in.exception.UserAlreadyExistException;
import com.cs319.stack_in.service.AuthService;
import com.cs319.stack_in.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRequest authRequest, HttpServletRequest req) throws
            BusinessException {
        return ResponseEntity.ok(service.register(req));
    }
}
