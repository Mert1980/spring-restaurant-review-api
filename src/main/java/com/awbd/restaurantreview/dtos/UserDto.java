package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.domain.RoleType;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private UUID id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private RoleType type;

    private MultipartFile profilePicture;

    private String base64profilePicture;
}
