package com.awbd.restaurantreview.dtos;

import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDto {
    private UUID id;

    private String name;

    private Double rating;

    private MultipartFile logo;

    private String base64logo;

    private List<CategoryDto> categories;

    private AddressDto address;
}
