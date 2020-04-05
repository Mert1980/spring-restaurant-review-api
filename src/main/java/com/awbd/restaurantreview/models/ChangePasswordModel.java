package com.awbd.restaurantreview.models;

import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordModel {
    private UUID userId;

    private String newPassword;

    private String currentPassword;
}
