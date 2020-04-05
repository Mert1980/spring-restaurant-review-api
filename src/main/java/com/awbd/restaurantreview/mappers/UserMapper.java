package com.awbd.restaurantreview.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.modelmapper.ModelMapper;

import java.util.Base64;

import com.awbd.restaurantreview.domain.User;
import com.awbd.restaurantreview.dtos.UserDto;

@Component
public class UserMapper implements DomainMapper<User, UserDto> {

    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDto mapEntityToDto(User entity) {
        UserDto dto = mapper.map(entity, UserDto.class);
        StringBuilder base64 = new StringBuilder("data:image/png;base64,");
        base64.append(Base64.getEncoder().encodeToString(entity.getProfilePicture()));
        dto.setBase64profilePicture(base64.toString());
        return dto;
    }

    @Override
    public User mapDtoToEntity(UserDto dto) {
        User user = mapper.map(dto, User.class);
        return user;
    }



}
