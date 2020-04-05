package com.awbd.restaurantreview.dtos.response;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
    private UUID id;

    private UUID restaurantId;

    private UUID userId;

    private String userName;

    private String text;

    private Date createdAt;

    private int batroomQuality;

    private int staff;

    private int cleanliness;

    private int driveThru;

    private int deliverySpeed;

    private List<String> base64attachments;
}
