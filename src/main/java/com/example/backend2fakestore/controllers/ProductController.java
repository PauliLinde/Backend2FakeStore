package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    ProductService productService;

    @GetMapping("/product/getAll")
    public void getAllProducts(Model model){
        ProductDto products = productService.getAllProducts();

        model.addAttribute("products", products);
    }

}
