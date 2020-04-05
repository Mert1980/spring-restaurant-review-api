package com.awbd.restaurantreview.services;

import java.util.UUID;

import com.awbd.restaurantreview.domain.RatingType;
import com.awbd.restaurantreview.domain.Restaurant;

public interface RatingsService {
    void create(Restaurant restaurant);
    void update(UUID id, RatingType type, int stars);
    double calculateRatingForRestaurant(UUID restaurantId);
}
