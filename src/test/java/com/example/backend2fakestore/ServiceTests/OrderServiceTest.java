package com.example.backend2fakestore.ServiceTests;

import com.example.backend2fakestore.dtos.AdminOrderListDTO;
import com.example.backend2fakestore.mappers.OrderMapper;
import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import com.example.backend2fakestore.repository.OrderRepository;
import com.example.backend2fakestore.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import com.example.backend2fakestore.repository.UserRepository;
import com.example.backend2fakestore.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({OrderService.class, OrderMapper.class})
public class OrderServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;

    private ProductOrder testOrder;

    private AppUser testUser;

    @BeforeEach
    void setUp(){
        testUser = new AppUser();
        testUser.setUsername("test");
        testUser.setPassword("password");
        testUser.setRole("admin");
        entityManager.persist(testUser);

        Product.Rating rating = new Product.Rating(4.5, 100);
        testProduct = new Product();
        testProduct.setTitle("Test Backpack");
        testProduct.setPrice(599.00);
        testProduct.setDescription("A backpack to put all your tests into");
        testProduct.setCategory("Items");
        testProduct.setImage("https://fakestoreapi/backpack.jpg");
        testProduct.setRating(rating);
        entityManager.persist(testProduct);

        testOrder = new ProductOrder();
        testOrder.setDate(LocalDate.parse("2025-11-25"));
        testOrder.setProduct(testProduct);
        testOrder.setAppUser(testUser);

        entityManager.persist(testOrder);
        entityManager.flush();
    }

    @Test
    void getAllOrdersTest(){
        List<AdminOrderListDTO> allOrders = orderService.getAllOrders();

        assertTrue(allOrders.size() > 0);
    }

    @Test
    void createOrderTest(){
        String testUsername = "test";

        orderService.createOrder(testUsername, testProduct.getId());
        List<AdminOrderListDTO> allOrders = orderService.getAllOrders();
        String found = allOrders.stream().filter(s -> s.getUsername().equals("test")).findFirst().get().getUsername();
        assertTrue(allOrders.size() > 0);
        assertEquals(testUsername, found);

    }

    @Test
    void deleteOrderTest(){
        orderService.deleteOrder(testOrder.getId());

        List<AdminOrderListDTO> allOrders = orderService.getAllOrders();
        assertEquals(0, allOrders.size());
    }
}