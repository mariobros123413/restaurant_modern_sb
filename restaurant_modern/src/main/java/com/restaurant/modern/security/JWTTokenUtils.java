package com.restaurant.modern.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.restaurant.modern.entity.Usuario;

import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtils extends TokenUtils {
    public TokenPayload decodeToken(String authorizationHeader) {
        DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(getSecret().getBytes()))
                .build()
                .verify(authorizationHeader.replace(getTokenPrefix(), ""));

        return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(Usuario.Role.class));
    }
}