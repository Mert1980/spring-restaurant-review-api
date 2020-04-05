package com.awbd.restaurantreview.mappers;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.dtos.request.UserRequestDto;
import com.awbd.restaurantreview.dtos.response.UserResponseDto;

@Component
public class UserMapper implements Mapper<User, UserRequestDto, UserResponseDto> {
    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User mapDtoToEntity(UserRequestDto dto) {
        User user = mapper.map(dto, User.class);
        return user;
    }

    @Override
    public UserResponseDto mapEntityToDto(User entity) {
        UserResponseDto dto = mapper.map(entity, UserResponseDto.class);

        StringBuilder base64 = new StringBuilder(DATA_IMAGE);
        base64.append(Base64.getEncoder().encodeToString(entity.getProfilePicture()));
        dto.setBase64profilePicture(base64.toString());

        return dto;
    }
}
