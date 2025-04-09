package com.example.darpartmente.Department.Service.InterImp;

import com.example.darpartmente.Department.Model.Product;
import com.example.darpartmente.Department.Service.inter.ProductService;
import com.example.darpartmente.Department.repositery.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
 // ✅ CORRECT
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(MultipartFile file, String name, String description, int price) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
            productRepository.save(product);
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }


    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}

