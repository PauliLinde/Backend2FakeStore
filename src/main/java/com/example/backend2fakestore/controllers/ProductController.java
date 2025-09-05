package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.dtos.ProductDTO;
import com.example.backend2fakestore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/product/getAll")
    public String getAllProducts(Model model){
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("allProducts", products);
        return "products";
    }

}
