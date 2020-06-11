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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.awbd.restaurantreview.dtos.request.ReviewRequestDto;
import com.awbd.restaurantreview.dtos.response.ReviewResponseDto;
import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.services.ReviewService;

@RestController
@RequestMapping(value = "/api/restaurant/{restaurantId}/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = { "multipart/form-data" })
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> create(@PathVariable(value = "restaurantId") UUID restaurantId, @ModelAttribute @Valid ReviewRequestDto reviewDto) {
        reviewDto.setRestaurantId(restaurantId);
        reviewService.create(reviewDto);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<ReviewResponseDto>> read(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                                        @RequestParam(name = "sort", defaultValue = "DESC") String sort,
                                                        @RequestParam(name = "sortedParam", defaultValue = "createdAt") String sortedParam) throws BaseException {
        return ResponseEntity.ok(reviewService.read(PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortedParam)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewResponseDto> readById(@PathVariable("id") UUID id) throws BaseException {
        ReviewResponseDto review = reviewService.read(id);
        review.add(linkTo(methodOn(ReviewController.class).readById(id)).withSelfRel());
        review.add(linkTo(methodOn(RestaurantController.class).read(review.getRestaurantId())).withRel("restaurant"));
        return ResponseEntity.ok(review);
    }

    @PutMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> update(@PathVariable(value = "restaurantId") UUID restaurantId, @ModelAttribute @Valid ReviewRequestDto reviewDto) throws BaseException {
        reviewDto.setRestaurantId(restaurantId);
        reviewService.update(reviewDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) throws BaseException {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
