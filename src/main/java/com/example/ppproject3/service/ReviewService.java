package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.CreateReviewDto;
import com.example.ppproject3.controller.dto.CreateReviewResponseDto;
import com.example.ppproject3.controller.dto.GetReviewDto;
import com.example.ppproject3.errors.BookNotFoundError;
import com.example.ppproject3.errors.RatingOutOfRangeError;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.ReviewEntity;
import com.example.ppproject3.infrastructure.repository.BookRepository;
import com.example.ppproject3.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@PreAuthorize("isAuthenticated()")
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    public List<GetReviewDto> getAll() {
        List<ReviewEntity> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> new GetReviewDto(review.getBook(), review.getRating(), review.getComment(), review.getReviewDate()))
                .collect(Collectors.toList());
    }

    public GetReviewDto getOne(long id) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        return new GetReviewDto(review.getBook(), review.getRating(), review.getComment(), review.getReviewDate());
    }

    public CreateReviewResponseDto create(CreateReviewDto reviewDto) {
        BookEntity book = bookRepository.findById(reviewDto.getBookId()).orElseThrow(() -> BookNotFoundError.create(reviewDto.getBookId()));

        ReviewEntity review = new ReviewEntity();
        review.setBook(book);
        review.setRating(review.getRating());
        review.setComment(review.getComment());
        review.setReviewDate(review.getReviewDate());
        reviewRepository.save(review);

        return new CreateReviewResponseDto(review.getReviewId(), review.getBook(), review.getRating(), review.getComment(), review.getReviewDate());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}
