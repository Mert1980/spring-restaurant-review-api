package com.awbd.restaurantreview.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awbd.restaurantreview.domain.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, UUID> {

}
