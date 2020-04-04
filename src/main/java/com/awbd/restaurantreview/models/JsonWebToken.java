package com.awbd.restaurantreview.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JsonWebToken {

    private String accessToken;

    private String refreshToken;
}
