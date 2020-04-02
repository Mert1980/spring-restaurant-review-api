package com.awbd.restaurantreview.services;

public interface IdentityService  {
    void signUp(String email, String username, String password);
    void signIn(String email, String password);
    void changePassword(Long userId, String currentPassword, String newPassword);
}
