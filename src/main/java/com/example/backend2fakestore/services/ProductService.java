package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.Root;
import com.example.backend2fakestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productMapper.toDtoList(productRepository.findAll().stream().toList());
    }
}
