package com.example.webmd;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name(); //null поменяли на name (строковое представление)
    }
}
