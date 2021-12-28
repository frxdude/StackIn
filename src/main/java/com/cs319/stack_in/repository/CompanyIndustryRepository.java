package com.cs319.stack_in.repository;

import com.cs319.stack_in.entity.CompanyIndustry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * CompanyIndustryRepository
 *
 * @author Ariunaa Gantumur
 **/
public interface CompanyIndustryRepository extends CrudRepository<CompanyIndustry, Long> {
    List<CompanyIndustry> findByCompanyId(Long companyId);
    void deleteByCompanyId(Long companyId);
}
