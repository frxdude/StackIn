package com.cs319.stack_in.controller;

import com.cs319.stack_in.dto.request.company.AddCompanyRequest;
import com.cs319.stack_in.dto.request.company.UpdateCompanyRequest;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.service.CompanyService;
import com.cs319.stack_in.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * CompanyController
 *
 * @author Ariunaa Gantumur
 **/
@Api(tags = "Companies")
@RestController
@RequestMapping("companies")

public class CompanyController {
    CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@Valid @RequestBody  AddCompanyRequest addCompanyRequest, HttpServletRequest req)
            throws BusinessException {
        return ResponseEntity.ok().body(service.create(addCompanyRequest, req));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@Valid @RequestBody  UpdateCompanyRequest updateCompanyRequest, HttpServletRequest req)
            throws BusinessException {
        return ResponseEntity.ok().body(service.update(updateCompanyRequest, req));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{companyId}/industryTypes", method = RequestMethod.GET)
    public ResponseEntity<Object> getCompanyIndustryTypes(@PathVariable Long companyId, HttpServletRequest req)
            throws BusinessException {
        return ResponseEntity.ok().body(service.getCompanyIndustryTypes(companyId, req));
    }

}
