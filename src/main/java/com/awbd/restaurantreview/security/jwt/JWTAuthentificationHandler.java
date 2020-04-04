package com.awbd.restaurantreview.security.jwt;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public interface JWTAuthentificationHandler {

    String createToken(UUID id, String email);
    String validateToken(String token);
}
