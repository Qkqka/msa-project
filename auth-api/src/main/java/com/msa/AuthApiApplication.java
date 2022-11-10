package com.msa;

import org.springframework.boot.SpringApplication;

import com.msa.annotation.ApiApplicationAnnotation;

@ApiApplicationAnnotation
public class AuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApiApplication.class, args);
    }

}
