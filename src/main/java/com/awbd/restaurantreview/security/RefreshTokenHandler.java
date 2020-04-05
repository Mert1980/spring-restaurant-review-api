package com.awbd.restaurantreview.security;

import com.awbd.restaurantreview.exceptions.BaseException;

public interface RefreshTokenHandler {
    String createRefreshToken(String email) throws BaseException;
    boolean validateRefreshToken(String refreshToken) throws BaseException;
}
