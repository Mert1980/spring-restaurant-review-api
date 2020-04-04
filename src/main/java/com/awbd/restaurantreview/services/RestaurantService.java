package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.dtos.RestaurantDto;
import com.awbd.restaurantreview.exceptions.BaseException;

public interface RestaurantService {
    void create(RestaurantDto restaurantDto);
    RestaurantDto read(UUID id) throws BaseException;
    void update(RestaurantDto restaurantDto) throws BaseException;
    void delete(UUID id) throws BaseException;
}
