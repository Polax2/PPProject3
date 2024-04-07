package com.example.ppproject3.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class bookNotAvailableError {
    public static ResponseStatusException create(long id){

        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is not available for loaning");}
}
