package com.example.ppproject3.controller;

import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/api/books/mock-all")
    String getAll(){
        return "Mock all";
    }

    @GetMapping
    public List<GetReviewDto> getAllReview(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public GetReviewDto getOne(@PathVariable long id){
        return reviewService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody CreateReviewDto review){
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
