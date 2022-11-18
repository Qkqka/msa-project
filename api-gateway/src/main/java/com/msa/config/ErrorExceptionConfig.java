package com.msa.config;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.exception.GlobalExceptionHandler;

import lombok.RequiredArgsConstructor;

/**
 * https://tweety1121.tistory.com/entry/Spring-Cloud-Gateway-Global-Error-Handler
 * @author fnfnksb@gmail.com
 *
 */
//@Configuration
@RequiredArgsConstructor
public class ErrorExceptionConfig {

    private final ObjectMapper objectMapper;

    //@Bean
    public ErrorWebExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler(objectMapper);
    }
}