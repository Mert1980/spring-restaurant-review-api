package com.awbd.restaurantreview.controllers;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awbd.restaurantreview.dtos.request.RestaurantRequestDto;
import com.awbd.restaurantreview.dtos.response.RestaurantResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.services.RestaurantService;

@RestController
@RequestMapping(value = "/api/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> create(@ModelAttribute @Valid RestaurantRequestDto restaurantDto) {
        restaurantService.create(restaurantDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RestaurantResponseDto> read(@PathVariable("id") UUID id) throws BaseException {
        return ResponseEntity.ok(restaurantService.read(id));
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDto>> read(@RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                                            @RequestParam(name = "sort", defaultValue = "ASC") String sort,
                                                            @RequestParam(name = "sortedParam", defaultValue = "name") String sortedParam) throws BaseException {
        return ResponseEntity.ok(restaurantService.read(PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortedParam)));
    }

    @PutMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> update(@ModelAttribute @Valid RestaurantRequestDto restaurantDto) throws BaseException {
        restaurantService.update(restaurantDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws BaseException {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
