package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.comment.AddCommentRequest;
import com.cs319.stack_in.entity.Comment;
import com.cs319.stack_in.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CommentService
 *
 * @author Ariunaa Gantumur
 **/
public interface CommentService {

    List<Comment> getAllByRef(Long id, HttpServletRequest req);

    ResponseEntity<Object> deleteAllByRef(Long id, HttpServletRequest req);

    ResponseEntity<Object> delete(Long commentId, HttpServletRequest req);

    Comment create(AddCommentRequest addCommentRequest, Long refId, String refType, HttpServletRequest req);

    Comment update(String newComment, Long commentId, HttpServletRequest req) throws BusinessException;

    Comment get(Long id, HttpServletRequest req) throws BusinessException;
}
