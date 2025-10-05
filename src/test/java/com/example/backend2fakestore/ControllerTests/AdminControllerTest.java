package com.example.backend2fakestore.ControllerTests;

import com.example.backend2fakestore.controllers.AdminController;
import com.example.backend2fakestore.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_returnsAdminView() {
        when(orderService.getAllOrders()).thenReturn(null);

        String view = adminController.getAllOrders(model);

        verify(orderService).getAllOrders();
        verify(model).addAttribute(eq("orders"), any());
        assertEquals("admin", view);
    }

    @Test
    void deleteOrder_redirectsToAdmin() {
        int orderId = 1;
        String view = adminController.deleteOrder(orderId);

        verify(orderService).deleteOrder(orderId);
        assertEquals("redirect:/admin", view);
    }
}