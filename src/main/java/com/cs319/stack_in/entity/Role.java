package com.cs319.stack_in.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role
 *
 * @author Sainjargal Ishdorj
 **/

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_TEMP, ROLE_GROUP_ADMIN, ROLE_GROUP_OWNER;

    public String getAuthority() {
        return name();
    }

}
