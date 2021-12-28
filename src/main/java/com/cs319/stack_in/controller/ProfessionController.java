package com.cs319.stack_in.controller;

import com.cs319.stack_in.entity.Profession;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.ProfessionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessionController {

    ProfessionService service;

    @Autowired
    public ProfessionController(ProfessionService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Profession> getRootProfessions() {
        return service.getRootProfessions();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Profession> searchByName(@RequestParam(name = "name") String name) {
        return service.searchByName(name);
    }


    @RequestMapping(value = "/{parentId}", method = RequestMethod.GET)
    public List<Profession> getChildProfessions(@Valid @PathVariable Long parentId) throws BusinessException {
            return service.getChildProfessions(parentId);

    }




}
