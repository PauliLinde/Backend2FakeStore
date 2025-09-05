package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FakeStoreController {

    ProductRepository productRepository;
    FakeStoreService fakeStoreService;


    @GetMapping("/fetch")
    public ResponseEntity<?> fetchProducts() throws IOException {
        fakeStoreService.getItemsAndSave();
        return ResponseEntity.ok().body(productRepository.findAll());
    }
}
