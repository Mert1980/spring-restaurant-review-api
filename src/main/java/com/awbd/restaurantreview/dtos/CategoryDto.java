package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import javax.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private UUID id;

    @NotBlank(message = "Name field can't be empty.")
    @Size(max = 15, message = "Name can't be longer than 15 characters.")
    private String name;
}
