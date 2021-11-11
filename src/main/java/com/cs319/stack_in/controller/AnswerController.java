package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.answer.AnswerAddRequest;
import com.cs319.stack_in.dto.request.answer.AnswerUpdateRequest;
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
public class AnswerController {
    AnswerService service;

    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.GET)
    public ResponseEntity<Object> getByQuestionId(@PathVariable Long questionId, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.get(questionId, req));
    }

    @RequestMapping(value = "/{questionId}/answers", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable Long questionId, @Valid @RequestBody AnswerAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.add(questionId, addRequest, req));
    }

    @RequestMapping(value = "/{questionId}/answers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long questionId, @PathVariable Long id, @Valid @RequestBody AnswerUpdateRequest updateRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.update(questionId, id, updateRequest, req));
    }

    @RequestMapping(value = "/{questionId}/answers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long questionId, @PathVariable Long id, HttpServletRequest req) throws BusinessException {
        service.delete(questionId, id, req);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
