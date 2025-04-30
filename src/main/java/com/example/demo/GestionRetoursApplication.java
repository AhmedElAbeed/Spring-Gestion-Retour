package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.entities") 
@EnableJpaRepositories(basePackages = "com.example.repositories")
@ComponentScan(basePackages = "com.example")
public class GestionRetoursApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionRetoursApplication.class, args);
    }
}
