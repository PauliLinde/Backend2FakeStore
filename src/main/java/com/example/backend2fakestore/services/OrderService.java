package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;


    }

    public ProductOrder createOrder(AppUser user, Product product, int quantity) {

        ProductOrder order = new ProductOrder();
        order.setAppUser(user);
        order.setProduct(product);
        order.setDate(LocalDateTime.now());


        int total = (int) product.getPrice();
        order.setTotal(total);


        ProductOrder savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}


