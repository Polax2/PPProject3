package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public class CreateLoanDto {
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @NotNull
    private Date loanDate;
    @NotNull
    private Date dueDate;

    public CreateLoanDto() {
    }

    public CreateLoanDto(long bookId, long userId, Date loanDate, Date dueDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
