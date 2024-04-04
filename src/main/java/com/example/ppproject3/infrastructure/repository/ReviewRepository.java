package com.example.ppproject3.infrastructure.repository;

import com.example.ppproject3.infrastructure.entities.LoanEntity;
import com.example.ppproject3.infrastructure.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
