package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AnswerService
 *
 * @author Ariunaa Gantumur
 **/
public interface AnswerService {

    Answer add(Long questionId, AnswerAddRequest addRequest, HttpServletRequest req) throws BusinessException;

    ResponseEntity<Object> delete(Long questionId, Long answerId, HttpServletRequest req) throws BusinessException;

    ResponseEntity<Object> deleteAllByQuestion(Long questionId, HttpServletRequest req) throws BusinessException;

    List<Answer> getByQuestionId(Long questionId, HttpServletRequest req);

    Answer get(Long questionId, Long id, HttpServletRequest req) throws BusinessException;

    Answer update(Long questionId, Long id, AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException;

    List<Answer> getByUser(HttpServletRequest req) throws BusinessException;
}
