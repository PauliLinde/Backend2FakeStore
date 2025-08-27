package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class FakeStoreController {


    ProductRepository pRepository;
    FakeStoreService fStoreService;

    public FakeStoreController(ProductRepository pRepository, FakeStoreService fStoreService){
        this.pRepository = pRepository;
        this.fStoreService = fStoreService;
    }
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchProducts() throws IOException {
        fStoreService.getItemsAndSave();
        return ResponseEntity.ok().body(pRepository.findAll());
    }
}
