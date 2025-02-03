package com.example.demo.service;

import com.example.demo.dto.ProductMypriceRequestDto;
import com.example.demo.dto.ProductRequestDto;
import com.example.demo.entity.User;
import com.example.demo.naver.dto.Product;
import com.example.demo.naver.dto.ProductResponseDto;
import com.example.demo.naver.dto.ItemDto;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public static final int MIN_MY_PRICE = 100;


    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user) {

        Product product = productRepository.save(new Product(requestDto, user));

        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {

        int myprice = requestDto.getMyprice();

        if (myprice < MIN_MY_PRICE){
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다" + MIN_MY_PRICE + "원 이상으로 설정해 주세요");
        }

        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품을 찾을 수 없습니다")
        );

        product.update(requestDto);

        return new ProductResponseDto(product);


    }

    public List<ProductResponseDto> getProducts(User user) {
        List<Product> productList = productRepository.findAllByUser(user);
        List<ProductResponseDto> responseDtoList = new ArrayList<>();
        for (Product product : productList) {
            responseDtoList.add(new ProductResponseDto((product)));

        }
        return responseDtoList;
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {

        Product product = productRepository.findById(id).orElseThrow(()->
                new NullPointerException("해당 상품은 존재하지않습니다")
        );

        product.updateByItemDto(itemDto);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> responseDtoList = new ArrayList<>();
        for (Product product : productList) {
            responseDtoList.add(new ProductResponseDto((product)));

        }
        return responseDtoList;
    }
}
