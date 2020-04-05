package com.awbd.restaurantreview.security;

import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;

public interface RefreshTokenHandler {
    RefreshToken createRefreshToken(User user);
    boolean validateRefreshToken(RefreshToken refreshToken);
}
