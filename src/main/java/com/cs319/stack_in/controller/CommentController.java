package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.comment.AddCommentRequest;
import com.cs319.stack_in.entity.Comment;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.CommentService;
import com.cs319.stack_in.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * CommentController
 *
 * @author Ariunaa Gantumur
 **/
@Api(tags = "Comment")
@RestController
@RequestMapping("comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/ref/{refId}", method = RequestMethod.GET)
    public List<Comment> getByRefId(@PathVariable Long refId, HttpServletRequest req) throws BusinessException {
        return service.getAllByRef(refId, req);
    }

    @RequestMapping(value = "/ref/{refId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteByRefId(@PathVariable Long refId, HttpServletRequest req) throws BusinessException {
        return service.deleteAllByRef(refId, req);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Comment get(@PathVariable Long commentId, HttpServletRequest req) throws BusinessException {
        return service.get(commentId, req);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long commentId, HttpServletRequest req) throws BusinessException {
        return service.delete(commentId, req);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public Comment update(@Valid @RequestBody String comment, @PathVariable Long commentId, HttpServletRequest req) throws BusinessException {
        return service.update(comment, commentId, req);
    }

    @RequestMapping(value = "/questions/{refId}", method = RequestMethod.POST)
    public Comment createCommentForQuestion(@Valid @RequestBody AddCommentRequest addCommentRequest, @PathVariable Long refId, HttpServletRequest req) throws BusinessException {
        return service.create(addCommentRequest, refId, "question", req);
    }

    @RequestMapping(value = "/answers/{refId}", method = RequestMethod.POST)
    public Comment createCommentForAnswer(@Valid @RequestBody AddCommentRequest addCommentRequest, @PathVariable Long refId, HttpServletRequest req) throws BusinessException {
        return service.create(addCommentRequest, refId, "answer", req);
    }
}
