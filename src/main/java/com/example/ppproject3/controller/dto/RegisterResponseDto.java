package com.example.ppproject3.controller.dto;

import com.example.ppproject3.commonTypes.UserRole;

public class RegisterResponseDto {
    private String username;
    private UserRole role;

    private long userid;

    public RegisterResponseDto(String username, UserRole role, long userid) {
        this.username = username;
        this.role = role;
        this.userid = userid;
    }

    public RegisterResponseDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
