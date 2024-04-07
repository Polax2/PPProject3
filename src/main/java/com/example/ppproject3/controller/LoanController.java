package com.example.ppproject3.controller;
import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.errors.BookNotFoundError;
import com.example.ppproject3.errors.bookNotAvailableError;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.LoanEntity;
import com.example.ppproject3.infrastructure.repository.BookRepository;
import com.example.ppproject3.infrastructure.repository.LoanRepository;
import com.example.ppproject3.service.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Tag(name="Loan")
@RestController
@RequestMapping("/api/loans")
@PreAuthorize("isAuthenticated()")
public class LoanController {

    private final LoanService loanService;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanService loanService, BookRepository bookRepository, LoanRepository loanRepository){
        this.loanService = loanService;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }
    @PostMapping("/loans/{id}")
    public ResponseEntity<CreateLoanResponseDto> create(@PathVariable long id, @RequestBody @Validated CreateLoanDto loan){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> BookNotFoundError.create(id));

        if (book.getAvailableCopies() <= 0) {
            throw bookNotAvailableError.create(id);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        var newLoan = loanService.create(loan);

        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id){
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLoanDto> getOneById(@PathVariable long id){
        GetLoanDto dto = loanService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetLoansPageResponseDto> getAll(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetLoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable long id, Authentication authentication){
        loanService.returnBook(id, authentication);
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> BookNotFoundError.create(id));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

}
