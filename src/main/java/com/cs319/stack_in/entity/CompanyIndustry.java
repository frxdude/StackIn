package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * CompanyGenres
 *
 * @author Ariunaa Gantumur
 **/

@Entity
@Table(name = "CompanyIndustry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIndustry {

    @Id
    @SequenceGenerator(name = "companyIndustrySeq", sequenceName = "COMPANY_INDUSTRY_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "companyIndustrySeq")

    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long companyIndustryId;

    @Column(name = "COMPANY_ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long companyId;

    @Column(name = "INDUSTRY_TYPE", nullable = false)
    public String industry_type;
}
