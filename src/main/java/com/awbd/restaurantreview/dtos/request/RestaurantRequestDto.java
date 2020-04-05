package com.awbd.restaurantreview.dtos.request;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.dtos.AddressDto;
import com.awbd.restaurantreview.dtos.CategoryDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantRequestDto {
    private UUID id;

    @NotBlank(message = "Name field can't be empty.")
    @Size(max = 25, message = "Name can't be longer than 25 characters.")
    private String name;

    private MultipartFile logo;

    @NotEmpty(message = "Categories field can't be empty.")
    private List<CategoryDto> categories;

    private AddressDto address;
}
