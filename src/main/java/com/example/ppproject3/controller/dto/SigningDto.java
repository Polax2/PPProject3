package com.example.ppproject3.controller.dto;

public class SigningDto {

    private String token;

    public SigningDto(String token) {
        this.token = token;
    }

    public SigningDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
