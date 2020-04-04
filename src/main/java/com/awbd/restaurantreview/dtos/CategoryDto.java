package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private UUID id;

    private String name;
}
