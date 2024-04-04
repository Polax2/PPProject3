package com.example.ppproject3.infrastructure.repository;

import com.example.ppproject3.infrastructure.entities.LoanEntity;
import com.example.ppproject3.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
