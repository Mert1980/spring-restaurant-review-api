package com.awbd.restaurantreview.security.jwt;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.awbd.restaurantreview.domain.RefreshToken;
import com.awbd.restaurantreview.domain.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTRefreshTokenHandlerImpl implements JWTRefreshTokenHandler {

    @Value("${jwt.refreshTokenExpiryDays}")
    private int refreshTokenExpiryDays;

    @Override
    public RefreshToken createRefreshToken(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        return new RefreshToken(user, token);
    }

    @Override
    public boolean validateRefreshToken(RefreshToken refreshToken) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refreshToken.getCreatedAt());
        calendar.add(Calendar.DAY_OF_MONTH, refreshTokenExpiryDays);

        Date shouldExpireDate = calendar.getTime();
        Date currentDate = new Date(System.currentTimeMillis());

        if(currentDate.before(shouldExpireDate)) {
            return true;
        }

        return false;
    }



}
