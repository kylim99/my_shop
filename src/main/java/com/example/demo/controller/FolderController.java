package com.example.demo.controller;


import com.example.demo.dto.FolderRequestDto;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders (@RequestBody FolderRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        List<String> folderNames = requestDto.getFolderNames();
        folderService.addFolders(folderNames,userDetails.getUser());
    }
}
