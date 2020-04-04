package com.awbd.restaurantreview.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.exceptions.AlreadyExistsException;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.repositories.RefreshTokenRepository;
import com.awbd.restaurantreview.repositories.UserRepository;
import com.awbd.restaurantreview.security.jwt.JwtHandler;
import com.awbd.restaurantreview.security.jwt.RefreshTokenHandler;

@Service
public class IdentityServiceImpl implements IdentityService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IdentityServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtHandler jwtHandler, RefreshTokenHandler refreshTokenHandler) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void signUp(String firstName, String lastName, String email, String password) throws BaseException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new AlreadyExistsException("email", email);
        }
        String passwordHashed = bCryptPasswordEncoder.encode(password);
        User user = new User(firstName, lastName, email, passwordHashed);
        userRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword){

    }
}
