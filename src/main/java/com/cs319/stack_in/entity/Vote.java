package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Vote
 *
 * @author Ariunaa Gantumur
 **/
@Entity
@Table(name = "VOTES", indexes = {
        @Index(name = "vote_id_idx", columnList = "id", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote extends Audit {
    @Id
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "REF_TYPE", nullable = false)
    private String refType;

    @Column(name = "REF_ID", nullable = false)
    private Long refId;

    @Column(name = "VOTE", nullable = false)
    private int vote;

    @PrePersist
    public void prePersist() {
        setId(getId() == null ? System.currentTimeMillis() : getId());
    }
}
