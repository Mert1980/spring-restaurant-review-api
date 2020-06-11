package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awbd.restaurantreview.domain.RatingType;
import com.awbd.restaurantreview.domain.Review;
import com.awbd.restaurantreview.dtos.request.ReviewRequestDto;
import com.awbd.restaurantreview.dtos.response.ReviewResponseDto;
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
    public void create(ReviewRequestDto reviewDto) {
        Review review = mapper.mapDtoToEntity(reviewDto);

        ratingsService.update(reviewDto.getRestaurantId(), RatingType.BathroomQuality, reviewDto.getBatroomQuality());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.Cleanliness, reviewDto.getCleanliness());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.Staff, reviewDto.getStaff());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.DriveThru, reviewDto.getDriveThru());
        ratingsService.update(reviewDto.getRestaurantId(), RatingType.DeliverySpeed, reviewDto.getDeliverySpeed());

        reviewRepository.save(review);
    }

    @Override
    public Page<ReviewResponseDto> read(Pageable pageable) throws BaseException {
        Page<Review> reviewsPage = reviewRepository.findAll(pageable);

        return reviewsPage.map(new Function<Review, ReviewResponseDto>() {
            @Override
            public ReviewResponseDto apply(Review t) {
                ReviewResponseDto dto = mapper.mapEntityToDto(t);
                return dto;
            }
        });
    }

    @Override
    public ReviewResponseDto read(UUID id) throws BaseException {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new NotFoundException(id);
        }

        ReviewResponseDto reviewDto = mapper.mapEntityToDto(optionalReview.get());

        return reviewDto;
    }
    @Override
    public void update(ReviewRequestDto reviewDto) throws BaseException {
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
