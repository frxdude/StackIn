package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.AddVoteRequest;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * VoteController
 *
 * @author Ariunaa Gantumur
 **/
@Api(tags = "Votes")
@RestController
@RequestMapping("votes")
public class VoteController {
    QuestionService questionService;
    AnswerService answerService;

    @Autowired
    public VoteController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }
    @RequestMapping(value = "/answers/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_GROUP_ADMIN')")
    public ResponseEntity<Object> voteForAnswer(@PathVariable Long id, @Valid @RequestBody AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException {
        answerService.vote( id,addVoteRequest, req);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_GROUP_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Object> voteForQuestion(@PathVariable Long id, @RequestBody AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException {
        questionService.vote(id, addVoteRequest, req);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
