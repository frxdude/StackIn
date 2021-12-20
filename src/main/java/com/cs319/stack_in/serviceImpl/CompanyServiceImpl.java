package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.company.AddCompanyRequest;
import com.cs319.stack_in.dto.request.company.UpdateCompanyRequest;
import com.cs319.stack_in.entity.Company;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.CompanyRepository;
import com.cs319.stack_in.repository.QuestionRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * CompanyServiceImpl
 *
 * @author Ariunaa Gantumur
 **/
@Service
public class CompanyServiceImpl implements CompanyService {
    Localization localization;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    CompanyRepository repository;

    public CompanyServiceImpl(Localization localization, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, CompanyRepository repository) {
        this.localization = localization;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repository = repository;
    }

    @Override
    public Company create(AddCompanyRequest addCompanyRequest, HttpServletRequest req) throws BusinessException {
          User user = userRepository.findById(jwtTokenProvider.getIdFromReq(req))
                  .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "User not found"));

          Company company = Company.builder()
                          .name(addCompanyRequest.getName())
                                  .location(addCompanyRequest.getLocation())
                                          .imagePath(addCompanyRequest.getImagePath())
//                                                  .industryTypeList(addCompanyRequest.getIndustryTypeList())
                  .admin(user)
                  .build();

          repository.save(company);
        return company;
    }

    @Override
    public Company update(UpdateCompanyRequest updateCompanyRequest, HttpServletRequest req) throws BusinessException {
        return null;
    }
}
