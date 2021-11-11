package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Job
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "JOBS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job extends Audit {

    @Id
    @SequenceGenerator(name = "jobSeq", sequenceName = "JOB_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

}
