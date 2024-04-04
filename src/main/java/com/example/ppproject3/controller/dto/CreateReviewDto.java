package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class CreateReviewDto {

    private BookEntity book;
    private UserEntity user;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public CreateReviewDto() {
    }

    public CreateReviewDto(BookEntity book, UserEntity user, int rating, String comment, LocalDate reviewDate) {
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
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
