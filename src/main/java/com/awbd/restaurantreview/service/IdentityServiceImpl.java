package com.awbd.restaurantreview.service;

import com.awbd.restaurantreview.repositories.RefreshTokenRepository;
import com.awbd.restaurantreview.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public IdentityServiceImpl(final UserRepository userRepository, final RefreshTokenRepository refreshTokenRepository) {

        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void signUp(String email, String username, String password) {

    }

    @Override
    public void signIn(String email, String password){

    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword){

    }

}
