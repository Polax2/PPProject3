package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;

import java.time.LocalDate;
import java.util.Date;

public class CreateLoanResponseDto {

    private Long loanId;
    private long userId;
    private long bookId;
    private Date loanDate;
    private Date dueDate;


    public CreateLoanResponseDto() {
    }

    public CreateLoanResponseDto(Long loanId, long userId, long bookId, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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
