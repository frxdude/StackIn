package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Question
 * @author Ariunaa Gantumur
 */
@Entity
@Table(name="ANSWERS", indexes = {
        @Index(name = "answer_id_idx", columnList = "id", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer extends Audit{
    @Id
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @ManyToOne(targetEntity=Question.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID", nullable = false)
    private Question question;

    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @Column(name = "UP_VOTES", nullable = true)
    private Integer upVotes;

    @PrePersist
    public void prePersist() {
        setId(getId() == null ? System.currentTimeMillis() : getId());
    }
}
