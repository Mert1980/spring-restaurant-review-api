package com.awbd.restaurantreview.services;

public interface RefreshTokenService  {
    void createToken();
    void revokeToken();
}
