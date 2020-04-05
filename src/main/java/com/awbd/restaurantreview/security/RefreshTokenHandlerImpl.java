package com.awbd.restaurantreview.security;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awbd.restaurantreview.configurations.ApplicationProperties;
import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.repositories.RefreshTokenRepository;
import com.awbd.restaurantreview.repositories.UserRepository;

@Component
public class RefreshTokenHandlerImpl implements RefreshTokenHandler {
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenHandlerImpl(ApplicationProperties applicationProperties, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String createRefreshToken(String email) throws BaseException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(email);
        }
        User user= optionalUser.get();
        RefreshToken refreshToken = new RefreshToken(user, UUID.randomUUID().toString().replace("-", ""));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) throws BaseException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if(optionalRefreshToken.isEmpty()) {
            throw new NotFoundException(refreshToken);
        }
        RefreshToken token = optionalRefreshToken.get();
        Date tokenExpireDate = new Date(token.getCreatedAt().getTime() + applicationProperties.getSecurity().getRefreshTokenLifetime());
        if (new Date().before(tokenExpireDate)) {
            return true;
        }

        return false;
    }
}
