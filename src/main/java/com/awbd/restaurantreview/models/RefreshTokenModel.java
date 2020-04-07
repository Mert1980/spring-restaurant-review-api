package com.awbd.restaurantreview.models;

import lombok.*;

@Getter
@Setter
public class RefreshTokenModel extends JsonWebTokenModel {
    private String refreshToken;

    public RefreshTokenModel(String accessToken, String refreshToken, long expires) {
        super(accessToken, expires);
        this.refreshToken = refreshToken;
    }
}
