package com.awbd.restaurantreview.models;

import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginModel {
    private UUID id;

    private String email;

    private String password;
}
