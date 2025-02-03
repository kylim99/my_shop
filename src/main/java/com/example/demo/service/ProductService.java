package com.example.demo.service;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.naver.dto.ProductResponseDto;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {

        Product product = productRepository.save(new Product(requestDto));

        return new ProductResponseDto(product);
    }
}
