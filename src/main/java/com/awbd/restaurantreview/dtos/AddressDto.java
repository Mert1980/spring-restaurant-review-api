package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import javax.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private UUID id;

    @NotBlank(message = "Country field can't be empty.")
    @Size(max = 25, message = "Country can't be longer than 25 characters.")
    private String country;

    @NotBlank(message = "City field can't be empty.")
    @Size(max = 25, message = "City can't be longer than 25 characters.")
    private String city;

    @NotBlank(message = "Street field can't be empty.")
    @Size(max = 25, message = "Street can't be longer than 25 characters.")
    private String street;

    @NotBlank(message = "Number field can't be empty.")
    @Size(max = 5, message = "Number can't be longer than 5 characters.")
    private String number;

    private GeographicLocationDto location;
}
