package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private UUID id;

    private String country;

    private String city;

    private String street;

    private String number;

    private GeographicLocationDto location;
}
