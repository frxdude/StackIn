package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.comment.AddCommentRequest;
import com.cs319.stack_in.entity.Comment;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.AnswerRepository;
import com.cs319.stack_in.repository.CommentRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.AnswerService;
import com.cs319.stack_in.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CommentServiceImpl
 *
 * @author Ariunaa Gantumur
 **/
@Service
public class CommentServiceImpl implements CommentService {

    Localization localization;
    CommentRepository repository;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;


    @Autowired
    public CommentServiceImpl(Localization localization, JwtTokenProvider jwtTokenProvider,
                              CommentRepository repository, UserRepository userRepository,
                              QuestionRepository questionRepository, AnswerRepository answerRepository

    ) {
        this.localization = localization;
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<Object> deleteAllByRef(Long refId, HttpServletRequest req) {
        repository.deleteByRefId(refId);
        return ResponseEntity.ok().body("Амжилттай устгалаа");
    }

    //
    @Override
    public List<Comment> getAllByRef(Long commentId, HttpServletRequest req) {
        return repository.findByRefId(commentId);
    }

    //
    @Override
    public ResponseEntity<Object> delete(Long commentId, HttpServletRequest req) {
        repository.deleteById(commentId);
        return ResponseEntity.ok().body("Амжилттай устгалаа.");
    }

    @Override
    public Comment create(AddCommentRequest addCommentRequest, Long refId, String refType, HttpServletRequest req) throws BusinessException {
//
//        if(refType.equals("question")){
//            questionRepository.findById(refId)
//                    .orElseThrow(() ->  new BusinessException(localization.getMessage("question.not.found")));
//        }
//
//        if(refType.equals("answer")){
//            answerRepository.findById(refId)
//                    .orElseThrow(() ->  new BusinessException(localization.getMessage("answer.not.found")));
//        }
//
//        if(addCommentRequest.getParentId() != null) {
//            Comment c = repository.findById(addCommentRequest.getParentId())
//                    .orElseThrow(() -> new BusinessException(localization.getMessage("parent.comment.nf")));
//        }

        Comment comment = Comment.builder()
                .comment(addCommentRequest.getComment())
                .parentId(addCommentRequest.getParentId())
                .refId(refId)
                .refType(refType)
                .userId(jwtTokenProvider.getIdFromReq(req))
                .build();
        repository.save(comment);
        return comment;
    }

    @Override
    public Comment update(String newComment, Long commentId, HttpServletRequest req) throws BusinessException {
        Comment comment = repository.findById(commentId)
                .orElseThrow(() -> new BusinessException(localization.getMessage("comment.not.found"), "Comment not found"));
        comment.setComment(newComment);
        repository.save(comment);
        return comment;
    }

    @Override
    public Comment get(Long id, HttpServletRequest req) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
    }
}
