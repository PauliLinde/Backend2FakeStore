package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.dtos.ProductDto;
import com.example.backend2fakestore.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/product/getAll")
    public String getAllProducts(Model model){
        List<ProductDto> products = productService.getAllProducts();

        model.addAttribute("allProducts", products);
        model.addAttribute("title", "Products");
        model.addAttribute("name", "Product details");
        return "products.html";
    }

}
