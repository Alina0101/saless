package com.example.saless;

import com.example.saless.models.Customer;
import com.example.saless.repository.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalessApplication.class, args);
    }


}

