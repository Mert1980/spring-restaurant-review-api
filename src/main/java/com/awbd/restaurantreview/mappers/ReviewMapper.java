package com.awbd.restaurantreview.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.modelmapper.ModelMapper;

import com.awbd.restaurantreview.domain.Attachment;
import com.awbd.restaurantreview.domain.AttachmentType;
import com.awbd.restaurantreview.domain.Review;
import com.awbd.restaurantreview.dtos.ReviewDto;

@Component
public class ReviewMapper implements DomainMapper<Review, ReviewDto> {
    private static final String DATA_IMAGE = "data:image/png;base64,";
    private static final String DATA_VIDEO = "data:video/mp4;base64,";

    private final ModelMapper mapper;

    @Autowired
    public ReviewMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ReviewDto mapEntityToDto(Review entity) {
        ReviewDto dto = mapper.map(entity, ReviewDto.class);

        List<String> base64attachments = new ArrayList<>();
        for(Attachment attachment : entity.getAttachments()) {
            StringBuilder base64 = new StringBuilder();
            if (attachment.getType() == AttachmentType.Image) {
                base64.append(DATA_IMAGE);
            } else {
                base64.append(DATA_VIDEO);
            }

            base64.append(Base64.getEncoder().encodeToString(attachment.getContent()));
            base64attachments.add(base64.toString());
        }
        dto.setBase64attachments(base64attachments);

        return dto;
    }

    @Override
    public Review mapDtoToEntity(ReviewDto dto) {
        Review review = mapper.map(dto, Review.class);

        try {
            List<Attachment> attachments = new ArrayList<>();
            for(MultipartFile file : dto.getAttachments()) {
                Attachment attachment = new Attachment();
                attachment.setFileName(file.getOriginalFilename());
                attachment.setContent(file.getBytes());
                if (file.getOriginalFilename().endsWith(".png")) {
                    attachment.setType(AttachmentType.Image);
                } else {
                    attachment.setType(AttachmentType.Video);
                }
                attachments.add(attachment);
            }
        } catch (Exception e) {

        }

        return review;
    }

}
