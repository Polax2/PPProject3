package com.example.ppproject3.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

    public class UserNotFoundError {

        public static ResponseStatusException createWithUsername(String username){
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this name already exist");}

        public static ResponseStatusException create(long id){
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id was not found");}
    }
