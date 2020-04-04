package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awbd.restaurantreview.domain.RatingType;
import com.awbd.restaurantreview.domain.Review;
import com.awbd.restaurantreview.dtos.ReviewDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.mappers.ReviewMapper;
import com.awbd.restaurantreview.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;
    private final RatingsService ratingsService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper mapper, RatingsService ratingsService) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.ratingsService = ratingsService;
    }

    @Transactional
    @Override
    public void create(ReviewDto reviewDto) {
        Review review = mapper.mapDtoToEntity(reviewDto);

        ratingsService.update(reviewDto.getRestaurantId(), RatingType.BathroomQuality, reviewDto.getBatroomQuality());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.Cleanliness, reviewDto.getCleanliness());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.Staff, reviewDto.getStaff());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.DriveThru, reviewDto.getDriveThru());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.DeliverySpeed, reviewDto.getDeliverySpeed());

        reviewRepository.save(review);
    }

    @Override
    public ReviewDto read(UUID id) throws BaseException {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new NotFoundException(id);
        }

        return mapper.mapEntityToDto(optionalReview.get());
    }

    @Override
    public void update(ReviewDto reviewDto) throws BaseException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewDto.getId());
        if (optionalReview.isEmpty()) {
            throw new NotFoundException(reviewDto.getId());
        }

        Review restaurant = mapper.mapDtoToEntity(reviewDto);
        reviewRepository.save(restaurant);
    }

    @Override
    public void delete(UUID id) throws BaseException {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new NotFoundException(id);
        }

        reviewRepository.deleteById(id);
    }
}
