package com.example.ppproject3.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFoundError {
    public static ResponseStatusException create(long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found");}

}
