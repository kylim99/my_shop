package com.example.demo.naver.controller;


import com.example.demo.dto.ProductRequestDto;
import com.example.demo.naver.dto.ProductResponseDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto creatProduct(@RequestBody ProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }
}
