package com.awbd.restaurantreview.dtos.response;

import java.util.UUID;
import lombok.*;

import com.awbd.restaurantreview.domain.RoleType;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private RoleType type;

    private String base64profilePicture;
}
