package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.dtos.ReviewDto;
import com.awbd.restaurantreview.exceptions.BaseException;

public interface ReviewService {
    void create(ReviewDto reviewDto);
    ReviewDto read(UUID id) throws BaseException;
    void update(ReviewDto reviewDto) throws BaseException;
    void delete(UUID id) throws BaseException;
}
