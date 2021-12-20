package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Company
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "COMPANIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends Audit {
    @Id
    @SequenceGenerator(name = "companySeq", sequenceName = "COMPANY_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "companySeq")

    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "IMAGE_PATH", nullable = false)
    private String imagePath;

    @ManyToOne(targetEntity=User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = true)
    private User admin;

    @OneToMany(targetEntity = JobEmployment.class, cascade = CascadeType.REMOVE,
            orphanRemoval = true, mappedBy = "company", fetch = FetchType.LAZY)
    private List<JobEmployment> jobEmploymentList = new ArrayList<>();

}
