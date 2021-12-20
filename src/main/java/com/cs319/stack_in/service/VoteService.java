package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.AddVoteRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * VoteService
 *
 * @author Ariunaa Gantumur
 **/
public interface VoteService {
    Question voteQuestion(AddVoteRequest addVoteRequest, Long id, HttpServletRequest req) throws BusinessException;
    Answer voteAnswer(AddVoteRequest addVoteRequest, Long id, HttpServletRequest req) throws BusinessException;
    Boolean checkVote(Long refId, HttpServletRequest req);

}
