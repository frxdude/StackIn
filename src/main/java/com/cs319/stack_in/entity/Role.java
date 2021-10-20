package com.cs319.stack_in.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role
 *
 * @author Sainjargal Ishdorj
 **/

public enum  Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_USER;

    public String getAuthority() {
        return name();
    }

}
