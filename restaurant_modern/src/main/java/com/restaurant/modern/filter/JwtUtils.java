package com.restaurant.modern.filter;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private static final String SECRET_KEY = "miclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecreta"; // Change this to your secret key
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static SecretKey getSecretKey() {
        return KEY;
    }
}
