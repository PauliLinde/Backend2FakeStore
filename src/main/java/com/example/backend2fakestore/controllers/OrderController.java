package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {


    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getAllOrders(Model model){
        List<ProductOrder> orders = orderService.getAllOrders();

        if(orders == null){
            System.out.println("No orders found");
        }

        System.out.println("Antal orders: " + orders.size()); // Debug

        model.addAttribute("allOrders", orders);
        model.addAttribute("title", "All orders");
        return "admin.html";
    }

    @GetMapping("/addOrder/{userId}/{productID}")
    public String addNewOrder(@PathVariable int userId,
            @PathVariable int productID, Model model){

        orderService.createOrder2(userId, productID);
        return "redirect:/";
    }

    @GetMapping("/admin/delete/{orderId}")
    public String deleteOrderGet(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin";
    }

}
