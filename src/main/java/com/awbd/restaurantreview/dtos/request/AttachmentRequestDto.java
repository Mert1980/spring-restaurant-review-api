package com.awbd.restaurantreview.dtos.request;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.domain.AttachmentType;

@AllArgsConstructor
@Getter
@Setter
public class AttachmentRequestDto {
    private UUID id;

    private String fileName;

    private AttachmentType type;

    private MultipartFile content;
}
