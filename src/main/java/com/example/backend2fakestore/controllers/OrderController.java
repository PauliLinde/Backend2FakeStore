package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.OrderService;
import com.example.backend2fakestore.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {


    private final OrderService orderService;
   private final  ProductRepository productRepository;

   public OrderController(OrderService orderService, ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
        }




    @GetMapping("/admin")
    public String getAllOrders(Model model) {
        List<ProductOrder> orders = orderService.getAllOrders();

        if (orders == null) {
            System.out.println("No orders found");
        }

        System.out.println("Antal orders: " + orders.size()); // Debug

        model.addAttribute("allOrders", orders);
        model.addAttribute("title", "All orders");
        return "admin.html";
    }

    @GetMapping("/addOrder/{userId}/{productID}")
    public String addNewOrder(@PathVariable int userId,
                              @PathVariable int productID, Model model) {

        orderService.createOrder2(userId, productID);
        return "redirect:/";
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestParam int productID,
                             @RequestParam int quantity,
                             Model model,
                             Authentication authentication) {
        try {
            Optional<Product> productOpt = productRepository.findById(productID);
            if (productOpt.isEmpty()) {
                return "redirect:/product/getAll?error=Product not found";

            }
            String username = authentication.getName(); //Logged-in username från Spring Security
            orderService.createOrder(username, productOpt.get(), quantity);
            return "redirect:/order-successful";

        } catch (Exception e) {
            return "redirect:/product/getAll?error=Failed to create order";
        }
    }

        @GetMapping("/order-successful")
        public String orderSuccessful (Model model){
            model.addAttribute("title", "Order Successful");
            return "order-successful";
        }



}