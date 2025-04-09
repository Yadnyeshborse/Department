package com.example.darpartmente.Department.Service.inter;

import com.example.darpartmente.Department.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void saveProduct(MultipartFile file, String name, String description,int price);
    Page<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id);
    void updateProduct(Product product);
    void deleteProductById(Long id);
    void save(Product product);
}

