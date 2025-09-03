package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {


    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/orders/getAll")
    public String getAllProducts(Model model){
        List<ProductOrder> orders = orderService.getAllOrders();

        model.addAttribute("allOrders", orders);
        model.addAttribute("title", "All orders");
        return "admin";
    }

}
