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
        this.productRepository=productRepository;
        this.userRepository=userRepository;
    }

    public List<ProductOrder> getAllOrders(){
        //return orderRepository.findAll();
        List<ProductOrder> orders = orderRepository.findAll();
        System.out.println("Antal orders: " + orders.size());
        if (!orders.isEmpty()) {
            System.out.println("Första order: " + orders.get(0).getProduct().getTitle());
        }
        return orders;
    }

    public ProductOrder createOrder(AppUser user, Product product) {

        ProductOrder order = new ProductOrder();
        order.setAppUser(user);
        order.setProduct(product);
        order.setDate(LocalDateTime.now());


        int total = (int) product.getPrice();
        order.setTotal(total);


        ProductOrder savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public void createOrder2(int userId, int productId) {

        AppUser user = userRepository.findById(userId).get();
        if(user == null) {
            System.out.println("User not found");
        }
        Product product = productRepository.findById(productId).get();
        if(product == null) {
            System.out.println("Product not found");
        }

        ProductOrder order = new ProductOrder();
        order.setAppUser(user);
        order.setProduct(product);
        order.setDate(LocalDateTime.now());

        int total = (int) product.getPrice();
        order.setTotal(total);

        orderRepository.save(order);
    }
}


