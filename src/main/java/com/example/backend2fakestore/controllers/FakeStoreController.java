package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class FakeStoreController {

    ProductRepository productRepository;
    FakeStoreService fakeStoreService;

    public FakeStoreController(ProductRepository pRepository, FakeStoreService fStoreService){
        this.productRepository = pRepository;
        this.fakeStoreService = fStoreService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchProducts() throws IOException {
        fakeStoreService.getItemsAndSave();
        return ResponseEntity.ok().body(productRepository.findAll());
    }
}
