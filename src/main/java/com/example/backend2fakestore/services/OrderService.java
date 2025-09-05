package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.OrderRepository;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public ProductOrder createOrder(String username, Product product, int quantity) {

        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        ProductOrder order = new ProductOrder();
                order.setAppUser(user);
                order.setProduct(product);
                order.setQuantity(quantity);
                order.setDate(LocalDate.now());
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
    }
}


