package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.AuthEntity;
import com.example.ppproject3.infrastructure.entities.LoanEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import com.example.ppproject3.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;

    private GetUserDto mapUser(UserEntity user) {
        return new GetUserDto(user.getUserId(), user.getName(), user.getSurname(), user.getEmail());
    }


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

    public GetUserDto getOneById(long id){
        AuthEntity auth = authRepository.findById(id).orElseThrow(() -> UserNotFoundError.create(id));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getUserId(), user.getName(), user.getSurname(), user.getEmail());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public GetUserPageResponseDto getAll(int page, int size) {
        Page<UserEntity> userPage;

        Pageable pageable = PageRequest.of(page, size);
        userPage = userRepository.findAll(pageable);

        List<GetUserDto> userDto = userPage.getContent().stream().map(this::mapUser).toList();
        return new GetUserPageResponseDto(userDto, userPage.getNumber(), userPage.getTotalElements(), userPage.getTotalPages(), userPage.hasNext());
    }
}
