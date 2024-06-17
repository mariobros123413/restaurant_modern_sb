package com.restaurant.modern.security;

public interface TokenGenerator {
    String build(Object id, Object role);
}