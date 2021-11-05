package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserService
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserService {

    AuthResponse resetPassword (Long id, HttpServletRequest req) throws BusinessException;
    List<Answer> getAnswers(Long id, HttpServletRequest req )throws BusinessException;
    List<Question> getQuestions(Long userId, HttpServletRequest req) throws BusinessException;

}
