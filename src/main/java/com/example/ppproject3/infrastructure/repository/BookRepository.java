package com.example.ppproject3.infrastructure.repository;

import com.example.ppproject3.infrastructure.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <BookEntity, Long >{
}
