package com.etsm.ETSM.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MANAGER,
    GUEST,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
