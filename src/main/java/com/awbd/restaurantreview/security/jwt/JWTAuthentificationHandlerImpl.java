package com.awbd.restaurantreview.security.jwt;

import java.util.Date;
import java.util.UUID;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthentificationHandlerImpl implements JWTAuthentificationHandler {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;


    @Override
    public String createToken(final UUID id, final String email) {
        return JWT.create()
        .withSubject(id.toString())
        .withClaim("email", email)
        .withIssuer(issuer)
        .withAudience(audience)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));
    }

    @Override
    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
        .build()
        .verify(token.replace(TOKEN_PREFIX, ""))
        .getSubject();
    }


}
