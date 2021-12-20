package com.cs319.stack_in.entity.redis;

import com.cs319.stack_in.controller.CompanyController;
import com.cs319.stack_in.entity.Company;
import com.cs319.stack_in.entity.IndustryType;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.service.CompanyService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * CompanyIndustry
 *
 * @author Ariunaa Gantumur
 **/


//@Entity
//@Table(name="COMPANY_INDUSTRY", indexes = {
//        @Index(name = "company_industry_id_idx", columnList = "id", unique = true),
//})
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class CompanyIndustry {

//    @Id
//    @Column(name = "ID")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private Long id;
//
//    @ManyToMany(targetEntity= Company.class, fetch= FetchType.EAGER)
//    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID", nullable = true)
//    private Company company;
//
//    @OneToMany(targetEntity=IndustryType.class, fetch=FetchType.EAGER)
//    @JoinColumn(name = "INDUSTRY_ID", referencedColumnName = "ID", nullable = true)
//    private IndustryType industryType;
//
//    @PrePersist
//    public void prePersist() {
//        setId(getId() == null ? System.currentTimeMillis() : getId());
//    }
}
