package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.AddVoteRequest;
import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AnswerService
 *
 * @author Ariunaa Gantumur
 **/
public interface AnswerService {
    Answer add(Long questionId, AnswerAddRequest addRequest, HttpServletRequest req) throws BusinessException;

    void delete(Long questionId, Long answerId, HttpServletRequest req) throws BusinessException;

    List<Answer> get(Long questionId, HttpServletRequest req);

    Answer update(Long questionId, Long id, AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException;

    void vote(Long id, AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException;
}
