package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.AddVoteRequest;
import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.repository.AnswerRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AnswerServiceImpl
 *
 * @author Ariunaa Gantumur
 **/

@Service
public class AnswerServiceImpl  implements AnswerService {

    UserRepository userRepository;
    QuestionRepository questionRepository;
    Localization localization;
    AnswerRepository repository;

    @Autowired
    public AnswerServiceImpl(UserRepository userRepository, Localization localization, AnswerRepository repository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.localization = localization;
        this.repository = repository;
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

            User user = userRepository.findById(addRequest.getUserId())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));

            Answer answer = repository.save(Answer.builder()
                    .answer(addRequest.getAnswer())
                    .question(question)
                    .user(user)
                    .upVotes(addRequest.getUpVotes())
                    .build());

            Logger.info(this.getClass().getName(), "[add][output][" + answer.toString() + "]");
            return null;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[add][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[add][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }

    }

    @Override
    public void delete(Long questionId, Long answerId, HttpServletRequest req) throws BusinessException {

        try {
            Logger.info(this.getClass().getName(), "[delete][input][questionId=" + answerId + "]");

            repository.findById(answerId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));

            repository.deleteById(answerId);
            Logger.info(this.getClass().getName(), "[delete][output][success]");
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[delete][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[delete][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public List<Answer> get(Long questionId, HttpServletRequest req) {
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
    public Answer update(Long questionId, Long id, AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][" + updateRequest.toString() + "]");
            Answer answer = repository.findById(id).orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));
            answer.setAnswer(updateRequest.getAnswer());
            repository.save(answer);
            Logger.info(this.getClass().getName(), "[update][output][" + answer.toString() + "]");
            return null;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public void vote(Long id, AddVoteRequest addVoteRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[vote][input][" + addVoteRequest.getVote() + "]");
            Answer answer = repository.findById(id).orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));
            int vote = answer.getUpVotes() != null ? answer.getUpVotes() : 0;
            answer.setUpVotes(vote +  addVoteRequest.getVote());

            repository.save(answer);
            Logger.info(this.getClass().getName(), "[vote][output][" + answer.toString() + "]");
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[vote][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
