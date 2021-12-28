package com.cs319.stack_in.service;

import com.cs319.stack_in.dto.request.company.AddCompanyRequest;
import com.cs319.stack_in.dto.request.company.UpdateCompanyRequest;
import com.cs319.stack_in.entity.Company;
import com.cs319.stack_in.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CompanyService
 *
 * @author Ariunaa Gantumur
 **/
public interface CompanyService {

    Company create(AddCompanyRequest addCompanyRequest, HttpServletRequest req) throws BusinessException;

    Company update(UpdateCompanyRequest addCompanyRequest, HttpServletRequest req) throws BusinessException;

    List<String> getCompanyIndustryTypes(Long companyId, HttpServletRequest req) throws BusinessException;

}
