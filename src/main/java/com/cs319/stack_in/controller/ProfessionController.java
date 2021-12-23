package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.question.QuestionAddRequest;
import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.entity.Question;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.ProfessionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * ProfessionController
 *
 * @author Ariunaa Gantumur
 **/
@Api(tags = "Profession")
@RestController
@RequestMapping("professions")
public class ProfessionController {
    ProfessionService service;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.service = professionService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Profession> getRootProfessions()  {
        return service.getRootProfessions();
    }

    @RequestMapping(value = "/{professionId}", method = RequestMethod.GET)
    public List<Profession> getChildProfessions(@PathVariable  Long professionId)  {
        return service.getProfessions(professionId);
    }


}
