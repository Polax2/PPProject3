package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.LoginDto;
import com.example.ppproject3.controller.dto.LoginResponseDto;
import com.example.ppproject3.controller.dto.RegisterDto;
import com.example.ppproject3.controller.dto.RegisterResponseDto;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.AuthEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import com.example.ppproject3.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto register(RegisterDto dto){
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());

        if (existingAuth.isPresent()){
            throw UserNotFoundError.createWithUsername(dto.getUsername());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        authEntity.setPassword(hashPassword);
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);
        return new RegisterResponseDto(authEntity.getUsername(), authEntity.getRole(), userEntity.getUserId());
    }


    public LoginResponseDto logIn(LoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDto(token);
    }

}
