package com.example.demo.service;


import com.example.demo.dto.FolderResponseDto;
import com.example.demo.entity.Folder;
import com.example.demo.entity.User;
import com.example.demo.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) throws IllegalAccessException {

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user,folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            if(!isExistFolderName(folderName,existFolderList)){
                Folder folder = new Folder(folderName,user);
                folderList.add(folder);
            }else{
                throw new IllegalAccessException("폴더명이 중복되었습니다");
            }
        }


        folderRepository.saveAll(folderList);
    }


    public List<FolderResponseDto> getFolders(User user) {

        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();
        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {

        for (Folder existFolder : existFolderList) {
            if (folderName.equals(existFolder.getName())){
                return true;
            }
        }
        return false;
    }
}
