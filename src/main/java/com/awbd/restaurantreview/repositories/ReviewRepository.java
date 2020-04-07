package com.awbd.restaurantreview.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awbd.restaurantreview.domain.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, UUID> {
    Page<Review> findAll(Pageable pageable);
}
