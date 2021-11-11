package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * User
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "USERS", indexes = {
        @Index(name = "user_id_idx", columnList = "id", unique = true),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Audit {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "UNIQUE_ID", nullable = false)
    private String uniqueId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @PrePersist
    public void prePersist() {
        setUniqueId(getUniqueId() == null ? RandomStringUtils.randomAlphabetic(24) : getUniqueId());
    }
}
