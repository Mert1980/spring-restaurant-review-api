package com.awbd.restaurantreview.services;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.awbd.restaurantreview.domain.Restaurant;
import com.awbd.restaurantreview.dtos.request.RestaurantRequestDto;
import com.awbd.restaurantreview.dtos.response.RestaurantResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.exceptions.NotFoundException;
import com.awbd.restaurantreview.mappers.RestaurantMapper;
import com.awbd.restaurantreview.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;
    private final RatingsService ratingsService;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper mapper,
            RatingsService ratingsService) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
        this.ratingsService = ratingsService;
    }

    @Override
    public void create(RestaurantRequestDto restaurantDto) {
        Restaurant restaurant = mapper.mapDtoToEntity(restaurantDto);
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
        Double rating = ratingsService.calculateRatingForRestaurant(optionalRestaurant.get().getId());
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
                Double rating = ratingsService.calculateRatingForRestaurant(t.getId());
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
}
