package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.GetUserDto;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.AuthEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthRepository authRepository;

    @Autowired
    public UserService(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public GetUserDto getUserByUsername(String username){
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundError.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getUserId(), user.getName(), user.getSurname(), user.getEmail());
    }
}
