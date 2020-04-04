package com.awbd.restaurantreview.services;

import com.awbd.restaurantreview.exceptions.BaseException;

public interface IdentityService  {
    void signUp(String firstName, String lastName, String email, String password) throws BaseException;
    void changePassword(Long userId, String currentPassword, String newPassword);
}
