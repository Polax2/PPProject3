package com.example.ppproject3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ppproject3.infrastructure.entities")
public class PpProject3Application {

    public static void main(String[] args) {
        SpringApplication.run(PpProject3Application.class, args);
    }

}
