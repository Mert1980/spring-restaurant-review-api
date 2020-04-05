package com.awbd.restaurantreview.services;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.awbd.restaurantreview.dtos.request.RestaurantRequestDto;
import com.awbd.restaurantreview.dtos.response.RestaurantResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;

public interface RestaurantService {
    void create(RestaurantRequestDto restaurantDto);
    RestaurantResponseDto read(UUID id) throws BaseException;
    Page<RestaurantResponseDto> read(Pageable pageable) throws BaseException;
    void update(RestaurantRequestDto restaurantDto) throws BaseException;
    void delete(UUID id) throws BaseException;
}
