package com.awbd.restaurantreview.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.Claim;

import com.awbd.restaurantreview.configurations.ApplicationProperties;
import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.exceptions.TokenRevokedException;
import com.awbd.restaurantreview.models.RefreshTokenModel;
import com.awbd.restaurantreview.repositories.RefreshTokenRepository;
import com.awbd.restaurantreview.repositories.UserRepository;
import com.awbd.restaurantreview.security.jwt.JwtHandler;

@Component
public class RefreshTokenHandlerImpl implements RefreshTokenHandler {
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtHandler jwtHandler;

    @Autowired
    public RefreshTokenHandlerImpl(ApplicationProperties applicationProperties, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, JwtHandler jwtHandler) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public String createRefreshToken(String email) throws BaseException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("email", email);
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
            throw new NotFoundException("refresh token", refreshToken);
        }

        RefreshToken token = optionalRefreshToken.get();
        if(token.getRevokedAt() != null) {
            return false;
        }

        Date tokenExpireDate = new Date(token.getCreatedAt().getTime() + applicationProperties.getSecurity().getRefreshTokenLifetime());
        if (new Date().before(tokenExpireDate)) {
            return true;
        }

        return false;
    }

    @Override
    public RefreshTokenModel createAccessToken(String refreshToken) throws BaseException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if (optionalRefreshToken.isEmpty()) {
            throw new NotFoundException("refresh token", refreshToken);
        }

        RefreshToken token = optionalRefreshToken.get();
        if (token.getRevokedAt() != null) {
            throw new TokenRevokedException(token.getId());
        }

        User user = token.getUser();
        Collection<? extends GrantedAuthority> authoritiesCollection = Arrays.stream(new String[] { user.getType().toString() })
                                                                   .map(SimpleGrantedAuthority::new)
                                                                   .collect(Collectors.toList());
        Set<String> authorities = AuthorityUtils.authorityListToSet(authoritiesCollection);
        String accessToken = jwtHandler.createToken(user.getEmail(), authorities);

        revoke(user.getId(), token.getToken());
        String newRefreshToken = createRefreshToken(user.getEmail());

        Long expires = null;
        Map<String,Object>  tokenPayload = jwtHandler.parseToken(accessToken);
        if(tokenPayload != null) {
            expires = ((Claim)tokenPayload.get("exp")).asLong();
        }

        return new RefreshTokenModel(accessToken, newRefreshToken, expires);
    }

    @Override
    public void revoke(UUID userId, String refreshToken) throws BaseException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if (optionalRefreshToken.isEmpty() || !optionalRefreshToken.get().getUser().getId().equals(userId)) {
            throw new NotFoundException("refresh token", refreshToken);
        }

        RefreshToken token = optionalRefreshToken.get();
        if (token.getRevokedAt() != null) {
            throw new TokenRevokedException(token.getId());
        }

        token.setRevokedAt(new Date());
        refreshTokenRepository.save(token);
    }
}
