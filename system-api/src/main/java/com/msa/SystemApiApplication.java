package com.msa;

import org.springframework.boot.SpringApplication;

import com.msa.annotation.ApiApplicationAnnotation;

@ApiApplicationAnnotation
public class SystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApiApplication.class, args);
    }

}
