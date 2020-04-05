package com.awbd.restaurantreview.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class JsonWebTokenModel {
    private String token;

    private long expires;
}
