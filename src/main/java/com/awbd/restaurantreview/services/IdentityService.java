package com.awbd.restaurantreview.services;

import com.awbd.restaurantreview.models.JsonWebToken;

public interface IdentityService  {
    void signUp(String firstName, String lastName, String email, String password);
    JsonWebToken signIn(String email, String password);
    void changePassword(Long userId, String currentPassword, String newPassword);
}
