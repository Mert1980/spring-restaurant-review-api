package com.awbd.restaurantreview.security.jwt;

import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;

import org.springframework.stereotype.Component;

@Component
public interface JWTRefreshTokenHandler {

    RefreshToken createRefreshToken(User user);
    boolean validateRefreshToken(RefreshToken refreshToken);

}
