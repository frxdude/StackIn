package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Space
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "SPACES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Space extends Audit {

    @Id
    @SequenceGenerator(name = "spaceSeq", sequenceName = "SPACE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "spaceSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "JOB_ID", nullable = false)
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "USER_UNIQUE_ID")
    private User user;

}
