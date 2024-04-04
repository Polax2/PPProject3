package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.CreateReviewDto;
import com.example.ppproject3.controller.dto.CreateReviewResponseDto;
import com.example.ppproject3.controller.dto.GetReviewDto;
import com.example.ppproject3.infrastructure.entities.ReviewEntity;
import com.example.ppproject3.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public List<GetReviewDto> getAll(){
        var reviews = reviewRepository.findAll();
        return reviews.stream().map((review) -> new GetReviewDto(review.getBook(), review.getRating(), review.getComment(), review.getReviewDate())).collect(Collectors.toList());
    }

    public GetReviewDto getOne(long id){
        var review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        return new GetReviewDto(review.getBook(), review.getRating(), review.getComment(), review.getReviewDate());
    }

    public CreateReviewResponseDto create(CreateReviewDto review){
        var reviewEntity = new ReviewEntity();
        reviewEntity.setBook(review.getBook());
        reviewEntity.setUser(review.getUser());
        reviewEntity.setRating(review.getRating());
        reviewEntity.setComment(review.getComment());
        reviewEntity.setReviewDate(review.getReviewDate());
        var newReview = reviewRepository.save(reviewEntity);

        return new CreateReviewResponseDto(newReview.getReviewId(), newReview.getBook(), newReview.getUser(), newReview.getRating(), newReview.getComment(), newReview.getReviewDate());
    }

    public void delete(long id){
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}
