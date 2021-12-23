package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.AddVoteRequest;
import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.dto.request.question.QuestionUpdateRequest;
import com.cs319.stack_in.dto.response.auth.AuthResponse;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.entity.Vote;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.QuestionService;
import com.cs319.stack_in.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    Localization localization;
    QuestionRepository repository;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public QuestionServiceImpl(Localization localization, QuestionRepository repository, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.localization = localization;
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * @param req servlet request
     * @return List of {@link Question}
     * @author Ariunaa
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
     * @param addRequest {@link QuestionAddRequest} DTO
     * @param req        servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException when User not found
     * @author Ariunaa
     **/
    public Question create(QuestionAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[add][input][" + addRequest.toString() + "]");

            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

            Long userId = jwtTokenProvider.getIdFromReq(req);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));

            Question question = repository.save(Question.builder()
                    .description(addRequest.getDescription())
                    .title(addRequest.getTitle())
                    .user(user)
                    .build());

            Logger.info(this.getClass().getName(), "[add][output][" + question.toString() + "]");
            return question;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[add][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[add][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public ResponseEntity<Object> deleteAll(List<Long> deleteIdList) throws BusinessException {

        for (Long id : deleteIdList) {
            Question question = repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("question.not.found")));
            repository.delete(question);
        }

        return ResponseEntity.ok().body(deleteIdList.size() + " асуулт амжилттай устгагдлаа.");
    }

    @Override
    public List<Question> updateAll(List<Question> questionList, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][" + questionList.toString() + "]");

            List<Question> result = new ArrayList<>();
            for (Question q : questionList) {
                Question question = repository.findById(q.getId()).orElseThrow(() ->
                        new BusinessException(localization.getMessage("question.not.found")));
                question.setDescription(q.getDescription());
                question.setTitle(q.getTitle());
                result.add(question);
                repository.save(question);
            }

            Logger.info(this.getClass().getName(), "[update][output][" + result.toString() + "]");
            return result;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }

    }

    /**
     * @param questionId Long
     * @param req        servlet request
     * @throws BusinessException when Data not found
     * @author Ariunaa
     **/

    public ResponseEntity<Object> delete(Long questionId, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[delete][input][questionId=" + questionId + "]");

            repository.findById(questionId)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));
            repository.deleteById(questionId);

            Logger.info(this.getClass().getName(), "[delete][output][success]");
            return ResponseEntity.ok().body("Амжилттай устгалаа");
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[delete][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[delete][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }


    /**
     * @param questionId Long
     * @param req        servlet request
     * @return {@link Question}
     * @throws BusinessException when Data not found
     * @author Ariunaa
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
    public Question update(Long id, QuestionUpdateRequest questionUpdateRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][" + questionUpdateRequest.toString() + "]");

            Question q = repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Question not found"));
            q.setDescription(questionUpdateRequest.getDescription());
            q.setTitle(questionUpdateRequest.getTitle());
            q.setVotes(questionUpdateRequest.getVotes());
            repository.save(q);

            Logger.info(this.getClass().getName(), "[update][output][" + q.toString() + "]");
            return q;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    @Override
    public List<Question> getByUser(HttpServletRequest req) throws BusinessException {
        return repository.findByUserId(jwtTokenProvider.getIdFromReq(req));
    }

    @Override
    public ResponseEntity deleteAllByUser(HttpServletRequest req) throws BusinessException {
        Long userId = jwtTokenProvider.getIdFromReq(req);
        List<Question> questionList = repository.findByUserId(userId);
        repository.deleteAll(questionList);
        return ResponseEntity.ok().body(questionList.size() + " асуултыг амжилттай устгалаа.");
    }
}
