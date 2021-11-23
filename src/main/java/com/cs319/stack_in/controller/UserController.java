package com.cs319.stack_in.controller;

import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.request.user.LoginRequest;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.validation.Valid;

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

    @RequestMapping(value = "/{id}/questions", method = RequestMethod.GET)
    public List<Question> getQuestions(@PathVariable Long id,
                                                HttpServletRequest req) throws BusinessException {
        List<Question> questionList = service.getQuestions(id, req);

        return questionList;
    }

    @RequestMapping(value = "/{id}/answers", method = RequestMethod.GET)
    public List<Answer> getAnswers(@PathVariable Long id,
                                   HttpServletRequest req) throws BusinessException {
        List<Answer> answerList = service.getAnswers(id, req);

        return answerList;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEMP')")
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRequest authRequest,
                                                HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.register(authRequest, req));
    }
}

