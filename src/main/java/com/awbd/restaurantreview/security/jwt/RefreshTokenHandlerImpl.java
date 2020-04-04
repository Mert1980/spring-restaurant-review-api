package com.awbd.restaurantreview.security.jwt;

import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;

import com.awbd.restaurantreview.configurations.ApplicationProperties;
import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;

@Component
public class RefreshTokenHandlerImpl implements RefreshTokenHandler {
    private final ApplicationProperties applicationProperties;

    public RefreshTokenHandlerImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        return new RefreshToken(user, token);
    }

    @Override
    public boolean validateRefreshToken(RefreshToken refreshToken) {
        Date tokenExpireDate = new Date(System.currentTimeMillis() + applicationProperties.getSecurity().getRefreshTokenLifetime());
        if (new Date().after(tokenExpireDate)) {
            return false;
        }

        return true;
    }
}
