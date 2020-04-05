package com.awbd.restaurantreview.dtos.response;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
    private UUID id;

    private UUID userId;

    private UUID restaurantId;

    private String userName;

    private String text;

    private Date createdAt;

    private Integer batroomQuality;

    private Integer staff;

    private Integer cleanliness;

    private Integer driveThru;

    private Integer deliverySpeed;

    private List<String> base64attachments;
}
