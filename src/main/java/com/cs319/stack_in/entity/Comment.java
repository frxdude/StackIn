package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Question
 *
 * @author Ariunaa Gantumur
 */
@Entity
@Table(name = "COMMENTS", indexes = {
        @Index(name = "comment_id_idx", columnList = "id", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends Audit {
    @Id
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "REF_ID", nullable = false)
    private Long refId;

    @Column(name = "REF_TYPE", nullable = false)
    private String refType;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "PARENT_ID", nullable = true)
    private Long parentId;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @PrePersist
    public void prePersist() {
        setId(getId() == null ? System.currentTimeMillis() : getId());
    }


}
