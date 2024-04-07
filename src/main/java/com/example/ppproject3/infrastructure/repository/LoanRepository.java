package com.example.ppproject3.infrastructure.repository;

import com.example.ppproject3.infrastructure.entities.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Page<LoanEntity> findByUserUserId(long userId, Pageable pageable);
    List<LoanEntity> findByUserUserId(long userId);
}
