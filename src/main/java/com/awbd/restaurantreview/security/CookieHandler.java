package com.awbd.restaurantreview.security;

import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awbd.restaurantreview.configurations.ApplicationProperties;

@Component
public class CookieHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh-token";
    public static final String REFRESH_TOKEN_COOKIE_PATH = "/api/account";
    private final ApplicationProperties applicationProperties;

    @Autowired
    public CookieHandler(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public Cookie create(String refreshToken) {
        Cookie refreshTokenCookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken);
        refreshTokenCookie.setMaxAge(applicationProperties.getSecurity().getRefreshTokenLifetime().intValue());
        refreshTokenCookie.setPath(REFRESH_TOKEN_COOKIE_PATH);

        return refreshTokenCookie;
    }
}
