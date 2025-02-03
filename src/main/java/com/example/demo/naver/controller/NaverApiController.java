package com.example.demo.naver.controller;




import com.example.demo.naver.dto.ItemDto;
import com.example.demo.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NaverApiController {

    private final NaverApiService naverApiService;

    public NaverApiController(NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }


    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam("query") String query)  {
        return naverApiService.searchItems(query);
    }
}