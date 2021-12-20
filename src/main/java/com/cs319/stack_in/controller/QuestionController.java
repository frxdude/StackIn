package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.dto.request.question.QuestionUpdateRequest;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.getAll(req));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Question create(@Valid @RequestBody QuestionAddRequest addRequest, HttpServletRequest req) throws BusinessException {
        return service.create(addRequest, req);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public List<Question> updateAll(@Valid @RequestBody List<Question> questionList, HttpServletRequest req) throws BusinessException {
        return service.updateAll(questionList, req);
    }

//    @RequestMapping( value ="" , method = RequestMethod.DELETE)
//    public ResponseEntity<Object> deleteAll(@Valid @RequestBody List<Long> deleteIdList) throws BusinessException {
//        return service.deleteAll( deleteIdList);
//    }

    @RequestMapping( value ="" , method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAllByUser(HttpServletRequest req) throws BusinessException {
        return service.deleteAllByUser( req);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable Long id, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.get(id, req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody QuestionUpdateRequest questionUpdateRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.update(id, questionUpdateRequest, req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest req) throws BusinessException {
           return service.delete(id, req);
    }
}
