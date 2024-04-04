package com.example.ppproject3.service;

import com.example.ppproject3.controller.dto.CreateBookDto;
import com.example.ppproject3.controller.dto.CreateBookResponseDto;
import com.example.ppproject3.controller.dto.GetBookDto;
import com.example.ppproject3.infrastructure.entities.BookEntity;
import com.example.ppproject3.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository =bookRepository;
    }
    public List<GetBookDto> getAll(){
        var books = bookRepository.findAll();
        return books.stream().map(this::mapBook).collect(Collectors.toList());
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
}
