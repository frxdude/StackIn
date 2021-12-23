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
@Table(name = "JOB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profession extends Audit {

    @Id
    @Column(name = "ID")
    private long id = System.currentTimeMillis();

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(targetEntity = Profession.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = true)
    private Long parentId;

}
