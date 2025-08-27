package com.example.backend2fakestore.services;

import com.example.backend2fakestore.dtos.ProductDto;
import com.example.backend2fakestore.mappers.ProductMapper;
import com.example.backend2fakestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;


    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts(){
        return productMapper.toDtoList(productRepository.findAll().stream().toList());
    }
}
