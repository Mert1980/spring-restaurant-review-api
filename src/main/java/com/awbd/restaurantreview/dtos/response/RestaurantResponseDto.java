package com.awbd.restaurantreview.dtos.response;

import java.util.List;
import java.util.UUID;
import lombok.*;

import com.awbd.restaurantreview.dtos.AddressDto;
import com.awbd.restaurantreview.dtos.CategoryDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantResponseDto {
    private UUID id;

    private String name;

    private Double rating;

    private String base64logo;

    private List<CategoryDto> categories;

    private AddressDto address;
}
