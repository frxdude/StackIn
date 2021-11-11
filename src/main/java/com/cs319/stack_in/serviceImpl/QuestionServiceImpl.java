package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.AddVoteRequest;
import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.dto.request.question.QuestionUpdateRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.QuestionService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    Localization localization;
    QuestionRepository repository;
    UserRepository userRepository;

    @Autowired
    public QuestionServiceImpl(Localization localization, QuestionRepository repository, UserRepository userRepository) {
        this.localization = localization;
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * @param addRequest {@link QuestionAddRequest} DTO
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException when User not found
     * @author ner
     **/

    public Question add(QuestionAddRequest addRequest, HttpServletRequest req)  throws BusinessException{
        try {
            Logger.info(this.getClass().getName(), "[add][input][" + addRequest.toString() + "]");

            User user = userRepository.findById(addRequest.getId())
                            .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));

            Question question = repository.save(Question.builder()
                    .description(addRequest.getDescription())
                    .title(addRequest.getTitle())
                    .upVotes(addRequest.getUpVotes())
                    .user(user)
                    .build());

            Logger.info(this.getClass().getName(), "[add][output][" + question.toString() + "]");
            return null;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[add][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[add][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param questionId Long
     * @param req servlet request
     * @throws BusinessException when Data not found
     * @author ner
     **/

    public void delete(Long questionId, HttpServletRequest req) throws BusinessException{
        try {
            Logger.info(this.getClass().getName(), "[delete][input][questionId=" + questionId + "]");

            repository.findById(questionId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));

            repository.deleteById(questionId);
            Logger.info(this.getClass().getName(), "[delete][output][success]");
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[delete][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[delete][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return List of {@link Question}
     * @author ner
     **/

    public List<Question> getAll(HttpServletRequest req) {
        try {
            Logger.info(this.getClass().getName(), "[getAll][input][]");
            List<Question> questionList = repository.findAllByOrderByCreatedDate();
            Logger.info(this.getClass().getName(), "[getAll][output][size=" + questionList.size() + "]");
            return questionList;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[getAll][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param questionId Long
     * @param req servlet request
     * @return {@link Question}
     * @throws BusinessException when Data not found
     * @author ner
     **/

    public Question get(Long questionId, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[get][input][questionId=" + questionId + "]");

            Question question = repository.findById(questionId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));

            Logger.info(this.getClass().getName(), "[get][output][" + question.toString() + "]");
            return question;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[get][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[get][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public Question update(QuestionUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][" + updateRequest.toString() + "]");

            User user = userRepository.findById(updateRequest.getUserId())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));

            Question question = repository.save(Question.builder()
                    .description(updateRequest.getDescription())
                    .title(updateRequest.getTitle())
                    .upVotes(updateRequest.getUpVotes())
                    .user(user)
                    .build());

            Logger.info(this.getClass().getName(), "[update][output][" + question.toString() + "]");
            return null;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[update][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }



    @Override
    public void vote(Long questionId, AddVoteRequest addVoteRequest, HttpServletRequest req) throws  BusinessException{

        Question question = repository.findById(questionId) .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
        int vote = question.getUpVotes() != null ? question.getUpVotes() : 0;
        question.setUpVotes(vote + addVoteRequest.getVote());
        repository.save(question);
    }


}
