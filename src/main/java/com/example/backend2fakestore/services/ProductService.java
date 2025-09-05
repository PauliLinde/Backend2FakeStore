package com.example.backend2fakestore.services;

import com.example.backend2fakestore.dtos.ProductDTO;
import com.example.backend2fakestore.mappers.ProductMapper;
import com.example.backend2fakestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        return productMapper.toDtoList(productRepository.findAll().stream().toList());
    }
}
