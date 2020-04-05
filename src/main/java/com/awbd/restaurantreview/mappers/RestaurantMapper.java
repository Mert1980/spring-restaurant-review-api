package com.awbd.restaurantreview.mappers;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.awbd.restaurantreview.domain.Restaurant;
import com.awbd.restaurantreview.dtos.request.RestaurantRequestDto;
import com.awbd.restaurantreview.dtos.response.RestaurantResponseDto;

@Component
public class RestaurantMapper implements Mapper<Restaurant, RestaurantRequestDto, RestaurantResponseDto> {
    private final ModelMapper mapper;

    @Autowired
    public RestaurantMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Restaurant mapDtoToEntity(RestaurantRequestDto dto) {
        Restaurant restaurant = mapper.map(dto, Restaurant.class);
        try {
            restaurant.setLogo(dto.getLogo().getBytes());
        } catch (Exception e) {

        }
        return restaurant;
    }

    @Override
    public RestaurantResponseDto mapEntityToDto(Restaurant entity) {
        RestaurantResponseDto dto = mapper.map(entity, RestaurantResponseDto.class);

        StringBuilder base64 = new StringBuilder(DATA_IMAGE);
        base64.append(Base64.getEncoder().encodeToString(entity.getLogo()));
        dto.setBase64logo(base64.toString());

        return dto;
    }
}
