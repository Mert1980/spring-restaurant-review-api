package com.awbd.restaurantreview.dtos;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.domain.AttachmentType;

@AllArgsConstructor
@Getter
@Setter
public class AttachmentDto {
    private UUID id;

    private String fileName;

    private AttachmentType type;

    private MultipartFile content;

    private String base64content;
}
