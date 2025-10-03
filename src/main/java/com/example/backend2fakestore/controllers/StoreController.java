package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.dtos.DisplayProductDTO;
import com.example.backend2fakestore.services.OrderService;
import com.example.backend2fakestore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final ProductService productService;
    private final OrderService orderService;

    @RequestMapping("/store")
    public String getAllProducts(Model model){
        List<DisplayProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "store";
    }

    @PostMapping("/store/order-successful")
    public String buyProduct(@RequestParam int productID,
                             Authentication authentication) {
        try {
            String username = authentication.getName();
            orderService.createOrder(username, productID);
        } catch (RuntimeException e) {
            return "redirect:/store?error=ProductOrUserNotFound";
        }
        return "order-successful";
    }

}
