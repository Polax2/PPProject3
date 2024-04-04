package com.example.ppproject3.controller;


import com.example.ppproject3.controller.dto.CreateBookDto;
import com.example.ppproject3.controller.dto.CreateBookResponseDto;
import com.example.ppproject3.controller.dto.GetBookDto;
import com.example.ppproject3.controller.dto.GetUserDto;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    // TODO: paging opcjonalnie
    public List<GetBookDto> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }

    @PostMapping
    // TODO: walidacja opcjonalnie
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody @Validated CreateBookDto book){
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    // TODO: zakupienie ksiązki, zmiana ilosci ksiazek to loan: search where return date = null
    public ResponseEntity<GetBookDto> updateUser(@PathVariable long id){
        return null;

    }



}