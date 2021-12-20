package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.AddVoteRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.Vote;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.service.QuestionService;
import com.cs319.stack_in.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    VoteService voteService;
    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    @RequestMapping(value = "/answers/{answerId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Answer voteForAnswer(@PathVariable Long answerId, @Valid @RequestBody AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException {
        return voteService.voteAnswer( addVoteRequest, answerId,  req);
    }

    @RequestMapping(value = "/questions/{questionId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Question voteForQuestion(@PathVariable Long questionId, @RequestBody AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException {
        return voteService.voteQuestion( addVoteRequest, questionId,  req);
    }


    @RequestMapping(value = "/check/{refId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Boolean checkVote(@PathVariable Long refId, HttpServletRequest req) throws BusinessException {
        return voteService.checkVote( refId,  req);
    }
}
