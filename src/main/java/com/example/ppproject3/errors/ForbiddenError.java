package com.example.ppproject3.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenError {
    public static ResponseStatusException create(){
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");}

}
