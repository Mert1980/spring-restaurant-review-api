package com.awbd.restaurantreview.dtos.request;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ReviewRequestDto {
    private UUID id;

    @NotNull(message = "User id can't be empty.")
    private UUID userId;

    @NotNull(message = "Restaurant id can't be empty.")
    private UUID restaurantId;

    @NotBlank(message = "Text field can't be empty.")
    @Size(max = 150, message = "Email can't be longer than 150 characters.")
    private String text;

    @NotNull(message = "Batroom quality field can't be empty.")
    @Size(min = 1, max = 5, message = "Batroom quality can't be less than 1 or more than 5.")
    private int batroomQuality;

    @NotNull(message = "Staff field can't be empty.")
    @Size(min = 1, max = 5, message = "Staff can't be less than 1 or more than 5.")
    private int staff;

    @NotNull(message = "Cleanliness field can't be empty.")
    @Size(min = 1, max = 5, message = "Cleanliness can't be less than 1 or more than 5.")
    private int cleanliness;

    @NotNull(message = "Drive thru field can't be empty.")
    @Size(min = 1, max = 5, message = "Drive thru can't be less than 1 or more than 5.")
    private int driveThru;

    @NotNull(message = "Delivery speed field can't be empty.")
    @Size(min = 1, max = 5, message = "Delivery speed can't be less than 1 or more than 5.")
    private int deliverySpeed;

    private List<MultipartFile> attachments;
}
