package com.example.ppproject3.controller;


import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Book")
@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping("/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetBookPageResponseDto> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetBookPageResponseDto dto = bookService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/{id}/getOne")
    @PreAuthorize("isAuthenticated()")
    public GetBookDto getOne(@PathVariable long id){
        return bookService.getOne(id);
    }

    @PostMapping("/createBook")
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody @Validated CreateBookDto book){
        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
    // TODO: zakupienie ksiązki, zmiana ilosci ksiazek to loan: search where return date = null

    @PatchMapping("/{id}/addNewCopies")
    public ResponseEntity<PatchBookResponseDto> updateBookCopies(@PathVariable long id, @RequestBody PatchBookDto dto){
        PatchBookResponseDto responseDto = bookService.updateBookCopies(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



}