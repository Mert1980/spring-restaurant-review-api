package com.awbd.restaurantreview.dtos.request;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.awbd.restaurantreview.domain.AttachmentType;
import com.awbd.restaurantreview.validations.EnumValidator;

@AllArgsConstructor
@Getter
@Setter
public class AttachmentRequestDto {
    private UUID id;

    @EnumValidator(enumClazz = AttachmentType.class, message = "Attachment type is not valid.")
    private String type;

    private MultipartFile content;
}
