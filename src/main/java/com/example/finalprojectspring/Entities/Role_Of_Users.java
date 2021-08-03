package com.example.finalprojectspring.Entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role_Of_Users implements GrantedAuthority {
    ADMIN,
    USER,
    MASTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
