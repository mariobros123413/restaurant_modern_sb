package com.restaurant.modern.security;




import org.apache.commons.lang3.builder.ToStringBuilder;

import com.restaurant.modern.entity.Usuario;

public class TokenPayload {
    private final String username;
    private final Usuario.Role role;

    public TokenPayload(String username, Usuario.Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Usuario.Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("role", role)
                .toString();
    }
}