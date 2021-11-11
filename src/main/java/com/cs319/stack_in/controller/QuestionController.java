package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**QuestionController
 *
 *
 * @author Ariunaa Gantumur
 */
@Api(tags = "Questions")
@RestController
@RequestMapping("questions")
public class QuestionController {
    QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {this.service = service;}

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> get(HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.getAll(req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable Long id, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.get(id, req));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@Valid @RequestBody QuestionAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.add(addRequest, req));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@Valid @RequestBody QuestionAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.update(addRequest, req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest req) throws BusinessException {
        service.delete(id, req);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
