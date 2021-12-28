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
@Table(name = "PROFESSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profession extends Audit {

    @Id
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;

    @Column(name = "NAME", nullable = false)
    public String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne(targetEntity = Profession.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = true)
    public Profession parentProfession;

    @PrePersist
    public void prePersist() {
        setId(getId() == null ? System.currentTimeMillis() : getId());
    }
}
