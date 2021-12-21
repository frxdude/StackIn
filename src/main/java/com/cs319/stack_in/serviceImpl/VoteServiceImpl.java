package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.AddVoteRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.entity.Vote;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.AnswerRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.VoteRepository;
import com.cs319.stack_in.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * VoreServiceImpl
 *
 * @author Ariunaa Gantumur
 **/
@Service
public class VoteServiceImpl implements VoteService {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    VoteRepository repository;
    Localization localization;
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    public VoteServiceImpl(Localization localization, JwtTokenProvider jwtTokenProvider, QuestionRepository questionRepository, AnswerRepository answerRepository, VoteRepository repository) {
        this.localization = localization;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    @Override
    public Question voteQuestion(AddVoteRequest addVoteRequest, Long id, HttpServletRequest req) throws BusinessException {
        Long userId = jwtTokenProvider.getIdFromReq(req);
        Question question = questionRepository.findById(id) .orElseThrow(() -> new BusinessException(localization.getMessage("question.not.found"), "Question not found"));


        Vote existedVote = repository.findByUserIdAndRefId(userId, id).orElse(null);
        if(existedVote != null){
            if(existedVote.getVote() == addVoteRequest.getVote()) {
                repository.delete(existedVote);
                question.setVotes(calcRemoveVote(question.getVotes(), existedVote.getVote()));
            } else {
                question.setVotes(calcRemoveVote(question.getVotes(), existedVote.getVote()));
                question.setVotes(calcAddVote(question.getVotes(), addVoteRequest.getVote()));
                existedVote.setVote(addVoteRequest.getVote());
                repository.save(existedVote);
            }
            questionRepository.save(question);
            return question;
        }

        question.setVotes(calcAddVote((question.getVotes() == null ? 0 : question.getVotes()), addVoteRequest.getVote()));
        questionRepository.save(question);
        Vote vote = Vote.builder()
                .vote(addVoteRequest.getVote())
                .refId(question.getId())
                .refType("question")
                .userId(userId)
                .build();
        repository.save(vote);
        return question;

    }
    @Override
    public Answer voteAnswer(AddVoteRequest addVoteRequest, Long id, HttpServletRequest req) throws BusinessException {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        localization.getMessage("answer.not.found")));

        Long userId = jwtTokenProvider.getIdFromReq(req);

        Vote existedVote = repository.findByUserIdAndRefId(userId, id).orElse(null);
        if(existedVote != null ){
            if(existedVote.getVote() == addVoteRequest.getVote()) {
                repository.delete(existedVote);
                answer.setVotes(calcRemoveVote(answer.getVotes(), existedVote.getVote()));
            } else {
                answer.setVotes(calcRemoveVote(answer.getVotes(), existedVote.getVote()));
                answer.setVotes(calcAddVote(answer.getVotes(), addVoteRequest.getVote()));
                existedVote.setVote(addVoteRequest.getVote());
                repository.save(existedVote);
            }
            answerRepository.save(answer);
            return answer;
        }
        answer.setVotes(calcAddVote((answer.getVotes() == null) ? 0 : answer.getVotes(), addVoteRequest.getVote()));
        answerRepository.save(answer);

        Vote vote = Vote.builder()
                .vote(addVoteRequest.getVote())
                .refId(answer.getId())
                .refType("answer")
                .userId(userId)
                .build();
        repository.save(vote);
        answer.setUser(null);
        return answer;
    }


    private int calcAddVote(int targetNum, int vote){
        int voteNum = targetNum != 0 ? targetNum : 0;
        return voteNum + vote;
    }

    private int calcRemoveVote(int targetNum, int vote){
        int voteNum = targetNum != 0 ? targetNum : 0;
        return voteNum - vote;
    }
    @Override
    public Boolean checkVote(Long refId, HttpServletRequest req){
        Long userId = jwtTokenProvider.getIdFromReq(req);
        boolean isPresent = repository.findByUserIdAndRefId(userId, refId)
                .isPresent();

        return isPresent;
    }



}