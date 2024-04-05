package com.example.ppproject3.controller;
import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.service.AuthService;
import com.example.ppproject3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal){
        String username = principal.getName();
        GetUserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchUserResponseDto> updateUser(@PathVariable long id, @RequestBody PatchUserDto dto){
        PatchUserResponseDto responseDto = userService.update(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    // TODO: pobieranie jednego usera po id do servisu dodać
    public ResponseEntity<GetUserDto> getOneById(@PathVariable long id){
        return null;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    //TODO: pobieranie listy plus paging do serwisu dodać
    public ResponseEntity<List<GetUserDto>> getAll(){
        return null;
    }

}
