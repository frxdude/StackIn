package com.cs319.stack_in.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Post
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "POSTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Blog {

    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

}
