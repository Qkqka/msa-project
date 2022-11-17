package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.Product;
import com.msa.model.Result;
import com.msa.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestProductController {

    private final ProductService productService;

    @GetMapping("/greeting")
    public String welcome() {
        return "hello, product-api";
    }

    @GetMapping("{prdId}")
    public Result getPrd(@PathVariable("prdId") String prdId) {
        Product prdInfo = productService.selectPrd(prdId);
        return new Result(prdInfo);
    }
}
