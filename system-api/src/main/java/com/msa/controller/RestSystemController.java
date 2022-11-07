package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class RestSystemController {

    @GetMapping("/greeting")
    public String welcome() {
        return "hello, system-api";
    }

    @GetMapping("/check")
    public String check() {
        return "hello, system-api check method.";
    }
}
