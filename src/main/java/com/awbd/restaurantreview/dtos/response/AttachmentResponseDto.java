package com.awbd.restaurantreview.dtos.response;

import java.util.UUID;
import lombok.*;

import com.awbd.restaurantreview.domain.AttachmentType;

@AllArgsConstructor
@Getter
@Setter
public class AttachmentResponseDto {
    private UUID id;

    private String fileName;

    private AttachmentType type;

    private String base64content;
}
