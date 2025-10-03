package com.example.backend2fakestore.ServiceTests;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.OrderRepository;
import com.example.backend2fakestore.repository.UserRepository;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private final String testUsername = "test";
    private final int testQuantity = 5;


    @Test
    void getAllOrdersTest(){
        // Mocka användaren
        AppUser mockUser = new AppUser();
        mockUser.setUsername(testUsername);
        when(userRepository.findByUsername(testUsername)).thenReturn(Optional.of(mockUser));

        Product mockProduct = new Product();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(mockProduct));

        List<ProductOrder> mockOrders = new ArrayList<>();
        mockOrders.add(new ProductOrder());
        when(orderRepository.findAll()).thenReturn(mockOrders);

        orderService.createOrder(testUsername, testQuantity);
        List<ProductOrder> allOrders = orderRepository.findAll();

        assertTrue(allOrders.size() > 0);
        assertFalse(allOrders.isEmpty());
    }

    @Test
    void createOrderTest(){
        AppUser mockUser = new AppUser();
        mockUser.setUsername(testUsername);
        when(userRepository.findByUsername(testUsername)).thenReturn(Optional.of(mockUser));

        Product mockProduct = new Product();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(mockProduct));

        orderService.createOrder(testUsername, testQuantity);

        verify(orderRepository, times(1)).save(any(ProductOrder.class));
    }

    @Test
    void deleteOrderTest(){
        int orderId = 1;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}