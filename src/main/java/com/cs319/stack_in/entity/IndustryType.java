package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

/**
 * IndustryType
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "INDUSTRY_TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustryType extends Audit {

    @Id
    @SequenceGenerator(name = "industryTypeSeq", sequenceName = "INDUSTRY_TYPE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "industryTypeSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "COMPANY_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Company company;

}
