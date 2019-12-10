package com.etsm.ETSM.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MANAGER,
    USER,
    GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
