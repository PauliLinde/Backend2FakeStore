package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.OrderRepository;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ProductOrder> getAllOrders() {
        //return orderRepository.findAll();
        List<ProductOrder> orders = orderRepository.findAll();
        System.out.println("Antal orders: " + orders.size());
        if (!orders.isEmpty()) {
            System.out.println("Första order: " + orders.get(0).getProduct().getTitle());
        }
        return orders;
    }

    public ProductOrder createOrder(String username, Product product, int quantity) {

AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

ProductOrder order = new ProductOrder();
        order.setAppUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setDate(LocalDateTime.now());
        order.setTotal(quantity);


        int total = (int) product.getPrice() * quantity;
        order.setTotal(total);


        ProductOrder savedOrder = orderRepository.save(order);
        return savedOrder;
    }


    //Ta bort order
    public void deleteOrder(int orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order ID not found");
        }

        orderRepository.deleteById(orderId);
        System.out.println("Order" + orderId + "deleted");
    }
}


