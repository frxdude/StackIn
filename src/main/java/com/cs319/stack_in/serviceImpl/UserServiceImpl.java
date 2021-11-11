package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.auth.AuthRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.Role;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.AnswerRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.UserService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserServiceImpl implements UserService {

    PasswordEncoder encoder;
    UserRepository repository;
    JwtTokenProvider jwtTokenProvider;
    Localization localization;
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository repository, JwtTokenProvider jwtTokenProvider, Localization localization, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.encoder = encoder;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.localization = localization;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * @param id  Long
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException
     * @author Sainjargal Ishdorj
     **/
    public AuthResponse resetPassword(Long id, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[login][input][" + "" + "]");
            Logger.info(this.getClass().getName(), "[login][output][" + "" + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[login][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public List<Question> getQuestions(Long id, HttpServletRequest req){
        List<Question> questionList = questionRepository.findByUserId(id);
        return questionList;
    }
    @Override
    public List<Answer> getAnswers(Long id, HttpServletRequest req){

        List<Answer> answerList = answerRepository.findByUserId(id);
        return answerList;
    }

}
