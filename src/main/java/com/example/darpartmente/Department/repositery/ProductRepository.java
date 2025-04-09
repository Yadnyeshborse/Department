package com.example.darpartmente.Department.repositery;

import com.example.darpartmente.Department.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

