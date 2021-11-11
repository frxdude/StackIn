package com.cs319.stack_in.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Group
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "GROUPS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends Audit {

    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "GROUP_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "groupSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SPACE_ID", nullable = false)
    private Long spaceId;

    @ManyToMany
    @JoinTable(
            name = "GROUP_OWNER",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<User> owners = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<User> users = new ArrayList<>();
}
