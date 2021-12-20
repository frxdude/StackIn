package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.comment.AddCommentRequest;
import com.cs319.stack_in.entity.Comment;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.CommentRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public CommentServiceImpl(Localization localization, JwtTokenProvider jwtTokenProvider,  CommentRepository repository, UserRepository userRepository) {
        this.localization = localization;
        this.repository = repository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity deleteAllByRef(Long refId, HttpServletRequest req) {
        repository.deleteByRefId(refId);
        return ResponseEntity.ok().body("Амжилттай устгалаа");
    }
//
    @Override
    public List<Comment> getAllByRef(Long commentId, HttpServletRequest req) {
        List<Comment> commentList = repository.findByRefId(commentId);
        return commentList;
    }
//
    @Override
    public ResponseEntity delete(Long commentId, HttpServletRequest req) {
        repository.deleteById(commentId);
        return ResponseEntity.ok().body("Амжилттай устгалаа.");
    }

    @Override
    public Comment create(AddCommentRequest addCommentRequest, Long refId, String refType, HttpServletRequest req) {
        Comment comment = Comment.builder()
                .comment(addCommentRequest.getComment())
                .parentId(addCommentRequest.getParentId())
                .refId(refId)
                .type(refType)
                .userId(jwtTokenProvider.getIdFromReq(req))
                .build();
        repository.save(comment);
        return comment;
    }

    @Override
    public Comment update(String newComment , Long commentId,  HttpServletRequest req) throws BusinessException {
        Comment comment = repository.findById(commentId)
                .orElseThrow(() -> new BusinessException(localization.getMessage("comment.not.found"), "Comment not found"));
            comment.setComment(newComment);
            repository.save(comment);
        return comment;
    }

    @Override
    public Comment get(Long id, HttpServletRequest req) throws BusinessException{
        return repository.findById(id).orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
    }
}
