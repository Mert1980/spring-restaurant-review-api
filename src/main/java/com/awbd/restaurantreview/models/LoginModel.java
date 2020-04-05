package com.awbd.restaurantreview.models;

import javax.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginModel {
    @Email
    @NotBlank(message = "Email field can't be empty.")
    private String email;

    @NotBlank(message = "Password field can't be empty.")
    private String password;
}
