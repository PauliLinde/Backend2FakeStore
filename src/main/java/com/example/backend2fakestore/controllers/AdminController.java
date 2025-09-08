package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.dtos.AdminOrderListDTO;
import com.example.backend2fakestore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @GetMapping("/admin")
    public String getAllOrders(Model model){
        List<AdminOrderListDTO> orders = orderService.getAllOrders();

        model.addAttribute("orders", orders);
        return "admin";
    }

    @GetMapping("/admin/delete/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin";
    }
}
