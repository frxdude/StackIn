package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

/**
 * Question
 *
 * @author Ariunaa Gantumur
 */
@Entity
@Table(name = "QUESTIONS", indexes = {
        @Index(name = "question_id_idx", columnList = "id", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends Audit {

    @Id
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CORRECT_ANSWER_ID", nullable = true)
    private Long correctAnswerId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = true)
    private User user;

    @Column(name = "VOTES", nullable = true)
    private Integer votes;

    public Question(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        setId(getId() == null ? System.currentTimeMillis() : getId());
    }
}
