package com.example.ppproject3.infrastructure.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<LoanEntity> loans;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthEntity auth;

    public UserEntity(Long userId, List<LoanEntity> loans, List<ReviewEntity> reviews, String email, String name, String surname, AuthEntity auth) {
        this.userId = userId;
        this.loans = loans;
        this.reviews = reviews;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.auth = auth;
    }

    public UserEntity() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }
}
