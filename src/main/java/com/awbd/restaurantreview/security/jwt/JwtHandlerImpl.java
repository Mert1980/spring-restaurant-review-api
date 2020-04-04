package com.awbd.restaurantreview.security.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.awbd.restaurantreview.configurations.ApplicationProperties;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtHandlerImpl implements JwtHandler {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ApplicationProperties applicationProperties;

    @Autowired
    public JwtHandlerImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public String createToken(String email, Set<String> authorities) {
        return JWT.create()
            .withSubject(email)
            .withClaim("roles", new ArrayList<String>(authorities))
            .withExpiresAt(new Date(System.currentTimeMillis() + applicationProperties.getSecurity().getJwt().getTokenLifetime()))
            .sign(HMAC512(applicationProperties.getSecurity().getJwt().getSecretKey().getBytes()));
    }

    @Override
    public Map<String, Object> parseToken(String token) {
        try {
            Map<String, Claim> claims = JWT.require(HMAC512(applicationProperties.getSecurity().getJwt().getSecretKey().getBytes()))
                    .build()
                    .verify(token)
                    .getClaims();

            return new HashMap<String, Object>(claims);
        } catch (Exception e) {
            logger.info("Failed to parse token.");
        }

        return null;
    }
}
