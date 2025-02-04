package com.example.demo.controller;


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
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails){


        return productService.getProducts(userDetails.getUser()
            ,page-1,size,sortBy,isAsc
        );
    }
    @PostMapping("/products/{productId}/folder")
    public void addFolder(
            @PathVariable("productId") Long productId,
            @RequestParam("folderId") Long folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IllegalAccessException {

        System.out.println(productId);
        System.out.println(folderId);
        productService.addFolder(productId, folderId, userDetails.getUser());

    }
    @GetMapping("/folders/{folderId}/products")
    public Page<ProductResponseDto> getProductInFolder(
            @PathVariable("folderId") Long folderId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return productService.getProductInFolder(
                folderId,
                page - 1,
                size,
                sortBy,
                isAsc,
                userDetails.getUser()
        );


    }

}
