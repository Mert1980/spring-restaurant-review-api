package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.dtos.request.ReviewRequestDto;
import com.awbd.restaurantreview.dtos.response.ReviewResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;

public interface ReviewService {
    void create(ReviewRequestDto reviewDto);
    ReviewResponseDto read(UUID id) throws BaseException;
    void update(ReviewRequestDto reviewDto) throws BaseException;
    void delete(UUID id) throws BaseException;
}
