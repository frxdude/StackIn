package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.dto.request.company.AddCompanyRequest;
import com.cs319.stack_in.dto.request.company.UpdateCompanyRequest;
import com.cs319.stack_in.entity.Company;
import com.cs319.stack_in.entity.CompanyIndustry;
import com.cs319.stack_in.entity.IndustryType;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.exception.BusinessException;
import com.cs319.stack_in.helper.Localization;
import com.cs319.stack_in.jwt.JwtTokenProvider;
import com.cs319.stack_in.repository.CompanyIndustryRepository;
import com.cs319.stack_in.repository.CompanyRepository;
import com.cs319.stack_in.repository.UserRepository;
import com.cs319.stack_in.repository.redis.IndustryTypeRepository;
import com.cs319.stack_in.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    CompanyIndustryRepository companyIndustryRepository;
    IndustryTypeRepository  industryTypeRepository;

    public CompanyServiceImpl(Localization localization, UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                              CompanyRepository repository, CompanyIndustryRepository companyIndustryRepository,
                              IndustryTypeRepository  industryTypeRepository
    ) {
        this.localization = localization;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repository = repository;
        this.companyIndustryRepository = companyIndustryRepository;
        this.industryTypeRepository = industryTypeRepository;
    }

    @Override
    public Company create(AddCompanyRequest addCompanyRequest, HttpServletRequest req) throws BusinessException {
        User user = userRepository.findById(jwtTokenProvider.getIdFromReq(req))
                .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));

        Company company = Company.builder()
                .name(addCompanyRequest.getName())
                .location(addCompanyRequest.getLocation())
                .imagePath(addCompanyRequest.getImagePath())
                .admin(user)
                .jobEmploymentList(null)
                .build();

        repository.save(company);

        for(String industryType: addCompanyRequest.getIndustryTypeList()){
            companyIndustryRepository.save(
                    new CompanyIndustry(null, company.getId(), industryType));
        }

        return company;
    }

    @Override
    public Company update(UpdateCompanyRequest updateCompanyRequest, HttpServletRequest req) throws BusinessException {

        Company company = repository.findById(updateCompanyRequest.getId())
                .orElseThrow(() -> new BusinessException(localization.getMessage("company.not.found")));


        company.setName(updateCompanyRequest.getName());
        company.setLocation(updateCompanyRequest.getLocation());
        company.setImagePath(updateCompanyRequest.getImagePath());

        repository.save(company);


        companyIndustryRepository.deleteByCompanyId(updateCompanyRequest.getId());

        for(String industryType: updateCompanyRequest.getIndustryTypeList()){
            companyIndustryRepository.save(
                    new CompanyIndustry(null, company.getId(), industryType));
        }

        return company;
    }

    @Override
    public List<String> getCompanyIndustryTypes(Long companyId, HttpServletRequest req) throws BusinessException {

        List<Long> types = companyIndustryRepository.findByCompanyId(companyId).stream()
                .map(CompanyIndustry::getCompanyIndustryId).collect(Collectors.toList());

        List<String> typesList = new ArrayList<>();
        for(Long id: types) {
            IndustryType industryType = industryTypeRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("industry.not.found")));
            typesList.add(industryType.getName());
        }

        return typesList;
    }
}
