package com.example.demo.repository;

import com.example.demo.entity.Folder;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder , Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
