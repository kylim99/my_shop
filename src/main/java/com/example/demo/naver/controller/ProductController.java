package com.example.demo.naver.controller;


import com.example.demo.dto.ProductMypriceRequestDto;
import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.createProduct(requestDto , userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id ,@RequestBody ProductMypriceRequestDto requestDto){
        System.out.println(id);
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProduct(
            @RequestParam("page") int page,
            @RequestParam("page") int size,
            @RequestParam("page") String sortBy,
            @RequestParam("page") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails){


        return productService.getProducts(userDetails.getUser()
            ,page-1,size,sortBy,isAsc
        );
    }
}
