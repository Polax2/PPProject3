package com.example.ppproject3.controller.dto;

import jakarta.persistence.Column;

public class CreateUserResponseDto {

    private Long userId;
    private String username;
    private String password;
    private String role;
    private String email;

    private String name;

    public CreateUserResponseDto() {
    }

    public CreateUserResponseDto(Long userId, String username, String password, String role, String email, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
