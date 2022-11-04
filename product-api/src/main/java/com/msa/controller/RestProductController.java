package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prd")
public class RestProductController {

    @GetMapping("/greeting")
    public String welcome() {
        return "hello, product-api";
    }
}
