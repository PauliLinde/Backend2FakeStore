package com.example.backend2fakestore.repository;

import com.example.backend2fakestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
