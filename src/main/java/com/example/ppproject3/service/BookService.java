package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.*;
import com.example.ppproject3.errors.BookNotFoundError;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import com.example.ppproject3.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public GetBookPageResponseDto getAll(int page, int size) {
        Page<BookEntity> bookPage;

        Pageable pageable = PageRequest.of(page, size);
        bookPage = bookRepository.findAll(pageable);

        List<GetBookDto> bookDto = bookPage.getContent().stream().map(this::mapBook).toList();
        return new GetBookPageResponseDto(bookDto, bookPage.getNumber(), bookPage.getTotalElements(), bookPage.getTotalPages(), bookPage.hasNext());
    }

    public GetBookDto getOne(long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return mapBook(book);
    }

    private GetBookDto mapBook(BookEntity book){
        return new GetBookDto(book.getId(), book.getIsbn(), book.getTitle(),book.getAuthor(), book.getPublisher(), book.getYearPublished(), book.getAvailableCopies() > 0);
    }

    public CreateBookResponseDto create(CreateBookDto book){
        var bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        bookEntity.setYearPublished(book.getYearPublished());
        var newBook = bookRepository.save(bookEntity);

        return new CreateBookResponseDto(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getYearPublished(), newBook.getAvailableCopies());
    }
    public void delete(long id){
        if(!bookRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookRepository.deleteById(id);
    }

    public PatchBookResponseDto updateBookCopies(long id, PatchBookDto dto){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> BookNotFoundError.create(id));

        book.setAvailableCopies(book.getAvailableCopies() + dto.getNewCopies());

        bookRepository.save(book);
        return new PatchBookResponseDto(book.getIsbn(   ), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getYearPublished(), book.getAvailableCopies());



    }
}
