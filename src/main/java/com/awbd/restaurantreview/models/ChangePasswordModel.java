package com.awbd.restaurantreview.models;

import java.util.UUID;
import javax.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordModel {
    @NotNull(message = "User id field can't be empty.")
    private UUID userId;

    @NotBlank(message = "New password field can't be empty.")
    private String newPassword;

    @NotBlank(message = "Current password field can't be empty.")
    private String currentPassword;
}
