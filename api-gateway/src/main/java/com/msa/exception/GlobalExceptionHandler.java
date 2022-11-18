package com.msa.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
//@Order(-1)
//@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // TODO Auto-generated method stub
        return null;
    }

}
