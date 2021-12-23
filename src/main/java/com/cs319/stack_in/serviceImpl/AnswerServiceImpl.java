package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.AnswerRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AnswerServiceImpl
 *
 * @author Ariunaa Gantumur
 **/

@Service
public class AnswerServiceImpl implements AnswerService {

    UserRepository userRepository;
    QuestionRepository questionRepository;
    Localization localization;
    AnswerRepository repository;
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AnswerServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider
            , Localization localization, AnswerRepository repository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.localization = localization;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * @param addRequest
     * @param req
     * @return
     * @throws BusinessException
     */
    @Override
    public Answer add(Long questionId, AnswerAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[add][input][" + addRequest.toString() + "]");
            Long userId = jwtTokenProvider.getIdFromReq(req);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found")));
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("question.not.found")));

            Answer answer = repository.save(Answer.builder()
                    .answer(addRequest.getAnswer())
                    .question(question)
                    .user(user)
                    .votes(addRequest.getVotes())
                    .build());

            Logger.info(this.getClass().getName(), "[add][output][" + answer.toString() + "]");
            return answer;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[add][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[add][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }

    }

    @Override
    public ResponseEntity delete(Long questionId, Long answerId, HttpServletRequest req) throws BusinessException {

        try {
            Logger.info(this.getClass().getName(), "[delete][input][questionId=" + answerId + "]");


            repository.findById(answerId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("answer.not.found")));
            repository.deleteById(answerId);
            Logger.info(this.getClass().getName(), "[delete][output][success]");

            return ResponseEntity.ok().body("Амжилттай усгалалаа");
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[delete][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[delete][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public ResponseEntity deleteAllByQuestion(Long questionId, HttpServletRequest req) throws BusinessException {
        Logger.info(this.getClass().getName(), "[delete][input][questionId=" + questionId + "]");

        List<Answer> answerList = repository.findByQuestionId(questionId);
        repository.deleteAll(answerList);

        return ResponseEntity.ok().body("Амжилттай устгалаа");

    }

    @Override
    public List<Answer> getByQuestionId(Long questionId, HttpServletRequest req) {
        try {
            Logger.info(this.getClass().getName(), "[getAll][input][]");
            List<Answer> questionList = repository.findByQuestionId(questionId);
            Logger.info(this.getClass().getName(), "[getAll][output][size=" + questionList.size() + "]");
            return questionList;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[getAll][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public Answer get(Long questionId, Long id, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[getAll][input][]");
            Answer answer = repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("answer.not.found")));
            Logger.info(this.getClass().getName(), "[getAll][output][size=" + answer + "]");
            return answer;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[getAll][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }


    @Override
    public Answer update(Long questionId, Long id, AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][" + updateRequest.toString() + "]");
            Answer answer = repository.findById(id).orElseThrow(() -> new BusinessException(localization.getMessage("answer.not.found")));
            answer.setAnswer(updateRequest.getAnswer());
            answer.setVotes(updateRequest.getVotes());
            repository.save(answer);

            Logger.info(this.getClass().getName(), "[update][output][" + answer.toString() + "]");
            return answer;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }


    @Override
    public List<Answer> getByUser(HttpServletRequest req) throws BusinessException {
        return repository.findByUserId(jwtTokenProvider.getIdFromReq(req));
    }
}
