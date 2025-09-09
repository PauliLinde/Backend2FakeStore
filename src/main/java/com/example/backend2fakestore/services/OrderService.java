package com.example.backend2fakestore.services;

import com.example.backend2fakestore.dtos.AdminOrderListDTO;
import com.example.backend2fakestore.mappers.OrderMapper;
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
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public List<AdminOrderListDTO> getAllOrders() {
        return orderMapper.productOrderListToAdminOrderDTOList
                (orderRepository.findAll());
    }

    //skapa order
    public void createOrder(String username, int productID) {
        AppUser user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository
                .findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductOrder order = new ProductOrder();
        order.setDate(LocalDate.now());
        order.setProduct(product);
        order.setAppUser(user);

        //spara order
        orderRepository.save(order);
    }

    //Ta bort order
    public void deleteOrder(int orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order ID not found");
        }
        orderRepository.deleteById(orderId);
    }
}


