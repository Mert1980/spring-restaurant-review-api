package com.awbd.restaurantreview.security.jwt;

import org.springframework.stereotype.Component;

import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;

@Component
public interface RefreshTokenHandler {
    RefreshToken createRefreshToken(User user);
    boolean validateRefreshToken(RefreshToken refreshToken);
}
