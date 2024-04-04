package com.example.ppproject3.controller.dto;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.time.LocalDate;
public class GetLoanDto {

    private long loanId;
    private GetBookDto book;
    private GetUserDto user;
    private Date loanDate;
    private Date dueDate;

    public GetLoanDto() {
    }

    public GetLoanDto(long loanId, GetBookDto book, GetUserDto user, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
        this.user = user;
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

