package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;

import java.time.LocalDate;
import java.util.Date;

public class GetReviewDto {
    private BookEntity book;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public GetReviewDto(BookEntity book, int rating, String comment, Date reviewDate) {
    }

    public GetReviewDto(BookEntity book, int rating, String comment, LocalDate reviewDate) {
        this.book = book;
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
