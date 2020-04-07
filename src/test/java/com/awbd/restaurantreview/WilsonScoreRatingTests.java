package com.awbd.restaurantreview;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import com.awbd.restaurantreview.repositories.RatingsRepository;
import com.awbd.restaurantreview.services.RatingsService;
import com.awbd.restaurantreview.services.RatingsServiceImpl;

@SpringBootTest
@ActiveProfiles("dev")
public class WilsonScoreRatingTests {
    private static final double MINIMUM_RATING = 0.0;
    private static final double MAXIMUM_RATING = 1.0;

    @TestConfiguration
    static class RatingsServiceImplTestConfiguration {
        @Autowired
        private RatingsRepository ratingsRepository;

        @Bean
        public RatingsService ratingsService() {
            return new RatingsServiceImpl(ratingsRepository);
        }
    }

    @Autowired
    private RatingsService ratingsService;

    @Test
    public void onlyOneStarRatings_shouldHave_zeroRating() {
        UUID restaurantId = UUID.fromString("B02131EB-CD78-41CC-B238-DA994BCCB740");

        Double rating = ratingsService.calculateRatingForRestaurant(restaurantId);

        Assert.isTrue(rating == MINIMUM_RATING, "Only one star ratings should be 0 general rating");
    }

    @Test
    public void fewerMaximumRatings_shouldNotEqual_maximumRating() {
        UUID restaurantId = UUID.fromString("79773198-E817-4097-B0E9-0683D7AF1088");

        Double rating = ratingsService.calculateRatingForRestaurant(restaurantId);

        Assert.isTrue(rating < MAXIMUM_RATING, "Rating should not be 1.0");
    }

    @Test
    public void fewerMaximumRatings_isLowerThan_moreAverageRatings() {
        UUID restaurantId1 = UUID.fromString("79773198-E817-4097-B0E9-0683D7AF1088");
        UUID restaurantId2 = UUID.fromString("C1EB1951-9507-4939-AF14-26F0B82398BC");

        double ratingLower = ratingsService.calculateRatingForRestaurant(restaurantId1);
        double ratingHigher = ratingsService.calculateRatingForRestaurant(restaurantId2);

        Assert.isTrue(ratingLower < ratingHigher, "Two maximum ratings should be lower than one hundred average ratings.");
    }
}
