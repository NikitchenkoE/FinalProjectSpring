package com.example.finalprojectspring.entities;

import org.springframework.security.core.GrantedAuthority;

public enum RoleOfUsers implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MASTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
