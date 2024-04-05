package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.GetUserDto;
import com.example.ppproject3.controller.dto.PatchUserDto;
import com.example.ppproject3.controller.dto.PatchUserResponseDto;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.AuthEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import com.example.ppproject3.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(AuthRepository authRepository, UserRepository userRepository){
        super(authRepository);
        this.userRepository = userRepository;
    }

    public GetUserDto getUserByUsername(String username){
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundError.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getUserId(), user.getName(), user.getSurname(), user.getEmail());
    }


    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")
    public PatchUserResponseDto update(long id, PatchUserDto dto){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> UserNotFoundError.create(id));

        dto.getEmail().ifPresent(user::setEmail);
        dto.getName().ifPresent(user::setName);
        dto.getLastName().ifPresent(user::setSurname);

        userRepository.save(user);
        return new PatchUserResponseDto( user.getName(), user.getSurname(), user.getEmail(), user.getUserId());

    }
}
