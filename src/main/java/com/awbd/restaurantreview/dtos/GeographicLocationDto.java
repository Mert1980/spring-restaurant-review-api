package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import javax.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeographicLocationDto {
    private UUID id;

    @NotNull(message = "Latitude field can't be empty.")
    private double latitude;

    @NotNull(message = "Longitude field can't be empty.")
    private double longitude;
}
