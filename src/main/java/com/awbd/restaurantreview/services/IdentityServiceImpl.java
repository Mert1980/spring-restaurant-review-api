package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;

import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.models.JsonWebToken;
import com.awbd.restaurantreview.repositories.RefreshTokenRepository;
import com.awbd.restaurantreview.repositories.UserRepository;
import com.awbd.restaurantreview.security.jwt.JWTAuthentificationHandler;
import com.awbd.restaurantreview.security.jwt.JWTRefreshTokenHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl implements IdentityService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTAuthentificationHandler jwtAuthentificationHandler;
    private final JWTRefreshTokenHandler jwtRefreshTokenHandler;

    @Autowired
    public IdentityServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTAuthentificationHandler jwtAuthentificationHandler, JWTRefreshTokenHandler jwtRefreshTokenHandler) {

        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtAuthentificationHandler = jwtAuthentificationHandler;
        this.jwtRefreshTokenHandler = jwtRefreshTokenHandler;
    }

    @Override
    public void signUp(String firstName, String lastName, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return; // TODO: exception
        }
        String passwordHashed = bCryptPasswordEncoder.encode(password);
        User newUser = new User(firstName, lastName, email, passwordHashed);
        newUser.setId(UUID.randomUUID());
        userRepository.save(newUser);
    }

    @Override
    public JsonWebToken signIn(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {

        }

        User actualUser = user.get();
        String accessToken = jwtAuthentificationHandler.createToken(actualUser.getId(), actualUser.getEmail());
        RefreshToken refreshToken = jwtRefreshTokenHandler.createRefreshToken(actualUser);
        refreshTokenRepository.save(refreshToken);
        return new JsonWebToken(accessToken, refreshToken.getToken());

    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword){

    }
}
