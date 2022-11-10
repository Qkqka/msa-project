package com.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.msa.annotation.ApiApplicationAnnotation;

@EnableFeignClients
@ApiApplicationAnnotation
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

}
