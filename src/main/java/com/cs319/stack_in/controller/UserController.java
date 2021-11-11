package com.cs319.stack_in.controller;

import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * UserController
 *
 * @author Sainjargal Ishdorj
 **/

@Api(tags = "User")
@RestController
@RequestMapping("users")
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "{userId}/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(@PathVariable(value = "userId") Long id,
                                                HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.resetPassword(id, req));
    }
}

