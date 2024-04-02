package com.example.fasthoandlee;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class FastHoAndLeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastHoAndLeeApplication.class, args);
    }
}
