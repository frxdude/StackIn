package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.AddVoteRequest;
import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.dto.request.question.QuestionUpdateRequest;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * QuestionService
 *
 * @author Ariunaa Gantumur
 */
public interface QuestionService {

    Question add(QuestionAddRequest addRequest, HttpServletRequest req) throws BusinessException;

    void delete(Long questionId, HttpServletRequest req) throws BusinessException;

    List<Question> getAll(HttpServletRequest req);

    Question get(Long questionId, HttpServletRequest req) throws BusinessException;

    Question update(QuestionUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException;
    void vote(Long questionId, AddVoteRequest addVoteRequest, HttpServletRequest req) throws  BusinessException;
}
