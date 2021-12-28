package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
import com.cs319.stack_in.entity.Answer;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.AnswerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * AnswerController
 *
 * @author Ariunaa Gantumur
 **/
@Api(tags = "Answers")
@RestController
@RequestMapping("questions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnswerController {
    AnswerService service;

    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.GET)
    public ResponseEntity<Object> getByQuestion(@PathVariable Long questionId, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.getByQuestionId(questionId, req));
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.POST)
    public Answer add(@PathVariable Long questionId, @Valid @RequestBody AnswerAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        return service.add(questionId, addRequest, req);
    }

    @RequestMapping(value = "/{questionId}/answers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long questionId, @PathVariable Long id, @Valid @RequestBody AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.update(questionId, id, updateRequest, req));
    }

    @RequestMapping(value = "/{questionId}/answers/{id}", method = RequestMethod.GET)
    public Answer get(@PathVariable Long questionId, @PathVariable Long id, HttpServletRequest req) throws BusinessException {
        return service.get(questionId, id, req);
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAllByQuestion(@PathVariable Long questionId, HttpServletRequest req) throws BusinessException {
        return service.deleteAllByQuestion(questionId, req);
    }

    @RequestMapping(value = "/{questionId}/answers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long questionId, @PathVariable Long id, HttpServletRequest req) throws BusinessException {
        return service.delete(questionId, id, req);
    }

}
