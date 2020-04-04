package com.awbd.restaurantreview.mappers;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.awbd.restaurantreview.domain.Restaurant;
import com.awbd.restaurantreview.dtos.RestaurantDto;

@Component
public class RestaurantMapper implements DomainMapper<Restaurant, RestaurantDto> {
    private final ModelMapper mapper;

    @Autowired
    public RestaurantMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public RestaurantDto mapEntityToDto(Restaurant entity) {
        RestaurantDto dto = mapper.map(entity, RestaurantDto.class);

        StringBuilder base64 = new StringBuilder("data:image/png;base64,");
        base64.append(Base64.getEncoder().encodeToString(entity.getLogo()));
        dto.setBase64logo(base64.toString());

        return dto;
    }

    @Override
    public Restaurant mapDtoToEntity(RestaurantDto dto) {
        Restaurant restaurant = mapper.map(dto, Restaurant.class);
        try {
            restaurant.setLogo(dto.getLogo().getBytes());
        } catch (Exception e) {

        }
        return restaurant;
    }
}
