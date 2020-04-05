package com.awbd.restaurantreview.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awbd.restaurantreview.domain.RatingType;
import com.awbd.restaurantreview.domain.Ratings;
import com.awbd.restaurantreview.domain.Restaurant;
import com.awbd.restaurantreview.repositories.RatingsRepository;

@Service
public class RatingsServiceImpl implements RatingsService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public void create(Restaurant restaurant) {
        for (RatingType type : RatingType.values()) {
            Ratings ratings = new Ratings();
            ratings.setType(type);
            ratings.setRestaurant(restaurant);
            ratings.setOneStarCount(0);
            ratings.setTwoStarCount(0);
            ratings.setThreeStarCount(0);
            ratings.setFourStarCount(0);
            ratings.setFiveStarCount(0);
            ratingsRepository.save(ratings);
        }
    }

    @Override
    public void update(UUID id, RatingType type, int stars) {
        Ratings ratings = ratingsRepository.findByRestaurantIdAndType(id, type);

        switch(stars) {
            case 1: ratings.setOneStarCount(ratings.getOneStarCount() + 1); break;
            case 2: ratings.setTwoStarCount(ratings.getTwoStarCount() + 1); break;
            case 3: ratings.setThreeStarCount(ratings.getThreeStarCount() + 1); break;
            case 4: ratings.setFourStarCount(ratings.getFourStarCount() + 1); break;
            case 5: ratings.setFiveStarCount(ratings.getFiveStarCount() + 1); break;
        }

        ratingsRepository.save(ratings);
    }

    @Override
    public double calculateRatingForRestaurant(UUID restaurantId) {
        List<Ratings> ratings = ratingsRepository.findAllByRestaurantId(restaurantId);
        double scoreTotal = 0.0;
        for (Ratings rating : ratings) {
            double score = wilsonScore(rating.getOneStarCount(), rating.getTwoStarCount(),
                    rating.getThreeStarCount(), rating.getFourStarCount(), rating.getFiveStarCount());
            logger.debug(String.format("RatingType: '%s' WilsonScore: '%s'", rating.getType(), score));
            scoreTotal += score;
        }
        double result = scoreTotal / ratings.size();
        logger.debug(String.format("Calculated rating is: '%s'", result));
        return result;
    }

    private double wilsonScore(int oneStarCount, int twoStarCount, int threeStarCount, int fourStarCount, int fiveStarCount) {
        // Calculate the positive score.
        // Normalize the rating scale to 0.0 - 1.0, giving more weight to higher
        // ratings.
        double p = (oneStarCount * 0.0) + (twoStarCount * 0.25) + (threeStarCount * 0.5) + (fourStarCount * 0.75)
                + (fiveStarCount * 1.0);
        // Calculate the negative score.
        // Normalize the rating scale to 0.0 - 1.0, giving more weight to lower ratings.
        double n = (oneStarCount * 1.0) + (twoStarCount * 0.75) + (threeStarCount * 0.5) + (fourStarCount * 0.25)
                + (fiveStarCount * 0.0);
        // Calculate the Wilson score confidence interval for a given positive score (p)
        // and negative score (n).
        double wilsonScore = p + n > 0
                ? ((p + 1.9208) / (p + n) - 1.96 * Math.sqrt((p * n) / (p + n) + 0.9604) / (p + n))
                        / (1 + 3.8416 / (p + n))
                : 0;

        return wilsonScore;
    }
}
