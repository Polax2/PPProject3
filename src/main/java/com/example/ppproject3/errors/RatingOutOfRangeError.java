package com.example.ppproject3.errors;

public class RatingOutOfRangeError extends RuntimeException {
    public RatingOutOfRangeError(String message) {
        super(message);
    }
}