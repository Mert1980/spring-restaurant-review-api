package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awbd.restaurantreview.domain.Restaurant;
import com.awbd.restaurantreview.dtos.request.RestaurantRequestDto;
import com.awbd.restaurantreview.dtos.response.RestaurantResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.mappers.RestaurantMapper;
import com.awbd.restaurantreview.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;
    private final RatingsService ratingsService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper mapper,
            RatingsService ratingsService, ResourceLoader resourceLoader) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
        this.ratingsService = ratingsService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void create(RestaurantRequestDto restaurantDto) {
        Restaurant restaurant = mapper.mapDtoToEntity(restaurantDto);
        if (restaurant.getLogo() == null) {
            restaurant.setLogo(defaultLogo());
        }

        ratingsService.create(restaurant);

        restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantResponseDto read(UUID id) throws BaseException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isEmpty()) {
            throw new NotFoundException(id);
        }

        RestaurantResponseDto restaurantDto = mapper.mapEntityToDto(optionalRestaurant.get());
        double rating = ratingsService.calculateRatingForRestaurant(optionalRestaurant.get().getId());
        restaurantDto.setRating(rating);

        return restaurantDto;
    }

    @Override
    public Page<RestaurantResponseDto> read(Pageable pageable) throws BaseException {
        Page<Restaurant> restaurantsPage = restaurantRepository.findAll(pageable);

        return restaurantsPage.map(new Function<Restaurant, RestaurantResponseDto>() {
            @Override
            public RestaurantResponseDto apply(Restaurant t) {
                RestaurantResponseDto dto = mapper.mapEntityToDto(t);
                double rating = ratingsService.calculateRatingForRestaurant(t.getId());
                dto.setRating(rating);

                return dto;
            }
        });
    }

    @Override
    public void update(RestaurantRequestDto restaurantDto) throws BaseException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantDto.getId());
        if (optionalRestaurant.isEmpty()) {
            throw new NotFoundException(restaurantDto.getId());
        }

        Restaurant restaurant = mapper.mapDtoToEntity(restaurantDto);
        if (restaurantDto.getLogo() != null) {
            try {
                restaurant.setLogo(restaurantDto.getLogo().getBytes());
            } catch (Exception e) {
                logger.info("Failed to update profile picture. Using default profile picture.");
                restaurant.setLogo(defaultLogo());
            }
        }

        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(UUID id) throws BaseException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isEmpty()) {
            throw new NotFoundException(id);
        }

        restaurantRepository.deleteById(id);
    }

    private byte[] defaultLogo() {
        byte[] defaultLogo = null;

        try {
            Resource resource = resourceLoader.getResource("classpath:restaurant_logo_default.png");
            defaultLogo = resource.getInputStream().readAllBytes();
        } catch (Exception e) {
            logger.info(String.format("Failed to read default logo. Message: %s", e.getMessage()));
        }

        return defaultLogo;
    }
}
