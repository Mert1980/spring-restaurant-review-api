package com.awbd.restaurantreview.controllers;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awbd.restaurantreview.dtos.request.ReviewRequestDto;
import com.awbd.restaurantreview.dtos.response.ReviewResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.services.ReviewService;

@RestController
@RequestMapping(value = "/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@ModelAttribute @Valid ReviewRequestDto reviewDto) {
        reviewService.create(reviewDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewResponseDto> read(@PathVariable("id") UUID id) throws BaseException {
        return ResponseEntity.ok(reviewService.read(id));
    }

    @PutMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> update(@ModelAttribute @Valid ReviewRequestDto reviewDto) throws BaseException {
        reviewService.update(reviewDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws BaseException {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
