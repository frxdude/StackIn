package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.auth.AuthRegisterRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.request.user.LoginRequest;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.service.QuestionService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    UserService service;
    QuestionService questionService;
    AnswerService answerService;

    @Autowired
    public UserController(UserService service, QuestionService questionService, AnswerService answerService) {
        this.service = service;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @RequestMapping(value = "{userId}/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(@PathVariable(value = "userId") Long id,
                                                HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.resetPassword(id, req));
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public List<Question> getQuestions(HttpServletRequest req) throws BusinessException {
        return questionService.getByUser(req);
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public List<Answer> getByUser(HttpServletRequest req) throws BusinessException {
        return answerService.getByUser(req);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEMP')")
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRegisterRequest authRegisterRequest,
                                           HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.register(authRegisterRequest, req));
    }
}

