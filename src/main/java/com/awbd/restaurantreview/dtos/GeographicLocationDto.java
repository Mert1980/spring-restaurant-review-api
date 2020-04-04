package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeographicLocationDto {
    private UUID id;

    private Double latitude;

    private Double longitude;
}
