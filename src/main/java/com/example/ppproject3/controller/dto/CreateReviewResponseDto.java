package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;

import java.time.LocalDate;

public class CreateReviewResponseDto {

    private Long reviewId;
    private BookEntity book;
    private UserEntity user;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public CreateReviewResponseDto() {
    }

    public CreateReviewResponseDto(Long reviewId, BookEntity book, UserEntity user, int rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
