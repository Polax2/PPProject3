package com.example.ppproject3.controller;

import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Review")
@RestController
@RequestMapping("/api/reviews")
@PreAuthorize("isAuthenticated()")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getAll")
    public List<GetReviewDto> getAllReviews() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}/getOne")
    public GetReviewDto getOne(@PathVariable long id) {
        return reviewService.getOne(id);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review) {
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
