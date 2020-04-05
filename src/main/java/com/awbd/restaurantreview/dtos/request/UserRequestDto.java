package com.awbd.restaurantreview.dtos.request;

import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.domain.RoleType;
import com.awbd.restaurantreview.validations.EnumValidator;

@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private UUID id;

    @Email
    @NotBlank(message = "Email field can't be empty.")
    @Size(max = 25, message = "Email can't be longer than 25 characters.")
    private String email;

    private String password;

    @NotBlank(message = "First name field can't be empty.")
    @Size(max = 25, message = "First name can't be longer than 25 characters.")
    private String firstName;

    @NotBlank(message = "Last name field can't be empty.")
    @Size(max = 25, message = "Last name can't be longer than 25 characters.")
    private String lastName;

    @EnumValidator(enumClazz = RoleType.class, message = "User type is not valid.")
    private String type;

    private MultipartFile profilePicture;
}
