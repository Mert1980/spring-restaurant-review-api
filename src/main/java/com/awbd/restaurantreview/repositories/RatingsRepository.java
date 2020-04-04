package com.awbd.restaurantreview.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.awbd.restaurantreview.domain.RatingType;
import com.awbd.restaurantreview.domain.Ratings;

@Repository
public interface RatingsRepository extends CrudRepository<Ratings, UUID> {
    @Query("SELECT r FROM Ratings r WHERE r.restaurant.id = :restaurantId")
    List<Ratings> findAllByRestaurantId(@Param("restaurantId") UUID restaurantId);

    @Query("SELECT r FROM Ratings r WHERE r.type = :type AND r.restaurant.id = :restaurantId")
    Ratings findByRestaurantIdAndType(@Param("restaurantId") UUID restaurantId, @Param("type") RatingType type);
}
