package com.example.backend2fakestore.ControllerTests;

import com.example.backend2fakestore.controllers.StoreController;
import com.example.backend2fakestore.dtos.DisplayProductDTO;
import com.example.backend2fakestore.services.OrderService;
import com.example.backend2fakestore.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StoreControllerTest {

    private ProductService productService;
    private OrderService orderService;
    private StoreController controller;
    private Model model;

    @BeforeEach
    void setup() {
        productService = mock(ProductService.class);
        orderService = mock(OrderService.class);
        model = mock(Model.class);
        controller = new StoreController(productService, orderService);
    }

    @Test
    void testGetAllProducts() {
        List<DisplayProductDTO> mockProducts = List.of(new DisplayProductDTO(1, "Product 1", "Desc", 10.0));
        when(productService.getAllProducts()).thenReturn(mockProducts);

        String viewName = controller.getAllProducts(model);

        assertEquals("store", viewName);
        verify(model).addAttribute("products", mockProducts);
    }

    @Test
    void testBuyProduct_Success() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");

        String viewName = controller.buyProduct(1, auth);

        assertEquals("order-successful", viewName);
        verify(orderService).createOrder("testuser", 1);
    }

    @Test
    void testBuyProduct_Failure() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");
        doThrow(new RuntimeException("error")).when(orderService).createOrder("testuser", 1);

        String viewName = controller.buyProduct(1, auth);

        assertEquals("redirect:/store?error=ProductOrUserNotFound", viewName);
    }
}