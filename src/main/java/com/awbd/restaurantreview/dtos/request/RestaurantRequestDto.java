package com.awbd.restaurantreview.dtos.request;

import java.util.List;
import java.util.UUID;
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

    private String name;

    private Double rating;

    private MultipartFile logo;

    private List<CategoryDto> categories;

    private AddressDto address;
}
