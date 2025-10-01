package com.example.backend2fakestore;

import com.example.backend2fakestore.dtos.AdminOrderListDTO;
import com.example.backend2fakestore.dtos.DisplayProductDTO;
import com.example.backend2fakestore.dtos.RegisterUserDTO;
import com.example.backend2fakestore.mappers.OrderMapper;
import com.example.backend2fakestore.mappers.ProductMapper;
import com.example.backend2fakestore.mappers.UserMapper;
import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.models.ProductOrder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MappersTest {

    private final OrderMapper orderMapper = new OrderMapper();
    private final ProductMapper productMapper = new ProductMapper();
    private final UserMapper userMapper = new UserMapper();

    Product testProduct1 = new Product
            (1, "Test Product 1", 9.99, "Product for testing",
                    "Testproducts", "fake_image_link", new Product.Rating(3.3, 5));
    Product testProduct2 = new Product
            (2, "Test Product 2", 9.99, "Product for testing",
                    "Testproducts", "fake_image_link", new Product.Rating(3.1, 3));

    AppUser testUser1 = new AppUser
            (1, "Test User 1", "password1", "Admin");
    AppUser testUser2 = new AppUser
            (2, "Test User 2", "password2", "Admin");

    ProductOrder testOrder1 = new ProductOrder
            (1, LocalDate.of(2025, 9, 23), testProduct1, testUser1);
    ProductOrder testOrder2 = new ProductOrder
            (2, LocalDate.of(2025, 9, 23), testProduct2, testUser2);

    @Test
    void productToDisplayProductDTO_success() {
        DisplayProductDTO mappedResult = productMapper.productToDisplayProductDTO(testProduct1);

        assertThat(mappedResult).isNotNull();
        assertThat(mappedResult.getId()).isEqualTo(testProduct1.getId());
        assertThat(mappedResult.getTitle()).isEqualTo(testProduct1.getTitle());
        assertThat(mappedResult.getPrice()).isEqualTo(testProduct1.getPrice());
        assertThat(mappedResult.getDescription()).isEqualTo(testProduct1.getDescription());
        assertThat(mappedResult.getCategory()).isEqualTo(testProduct1.getCategory());
        assertThat(mappedResult.getImage()).isEqualTo(testProduct1.getImage());
    }

    @Test
    void productListToDisplayProductDTOList_success() {
        List<Product> testProducts = Arrays.asList(testProduct1, testProduct2);
        List<DisplayProductDTO> mappedResultList = productMapper.productListToDisplayProductDTOList(testProducts);

        DisplayProductDTO mappedTest1 = productMapper.productToDisplayProductDTO(testProduct1);
        DisplayProductDTO mappedTest2 = productMapper.productToDisplayProductDTO(testProduct2);

        assertThat(mappedResultList).isNotNull();
        assertThat(mappedResultList.size()).isEqualTo(2);
        assertThat(mappedResultList.get(0)).usingRecursiveComparison().isEqualTo(mappedTest1);
        assertThat(mappedResultList.get(1)).usingRecursiveComparison().isEqualTo(mappedTest2);
    }

    @Test
    void productOrderToAdminOrderListDTO_success() {
        AdminOrderListDTO mappedResult = orderMapper.productOrderToAdminOrderListDTO(testOrder1);

        assertThat(mappedResult).isNotNull();
        assertThat(mappedResult.getId()).isEqualTo(testOrder1.getId());
        assertThat(mappedResult.getDate()).isEqualTo(testOrder1.getDate());
        assertThat(mappedResult.getProductTitle()).isEqualTo(testOrder1.getProduct().getTitle());
        assertThat(mappedResult.getUsername()).isEqualTo(testOrder1.getAppUser().getUsername());
    }

    @Test
    void productOrderListToAdminOrderDTOList_success() {
        List<ProductOrder> testProductOrders = Arrays.asList(testOrder1, testOrder2);
        List<AdminOrderListDTO> mappedResultList = orderMapper.productOrderListToAdminOrderDTOList(testProductOrders);

        AdminOrderListDTO mappedTest1 = orderMapper.productOrderToAdminOrderListDTO(testOrder1);
        AdminOrderListDTO mappedTest2 = orderMapper.productOrderToAdminOrderListDTO(testOrder2);

        assertThat(mappedResultList).isNotNull();
        assertThat(mappedResultList.size()).isEqualTo(2);
        assertThat(mappedResultList.get(0)).usingRecursiveComparison().isEqualTo(mappedTest1);
        assertThat(mappedResultList.get(1)).usingRecursiveComparison().isEqualTo(mappedTest2);
    }

    @Test
    void registerUserDTOToAppUser_success() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsername("Test User");
        registerUserDTO.setPassword("TestPassword1");
        registerUserDTO.setRole("Test Admin");

        AppUser mappedUser = userMapper.registerUserDTOToAppUser(registerUserDTO);

        assertThat(mappedUser).isNotNull();
        assertThat(mappedUser.getUsername()).isEqualTo(registerUserDTO.getUsername());
        assertThat(mappedUser.getPassword()).isEqualTo(registerUserDTO.getPassword());
        assertThat(mappedUser.getRole()).isEqualTo(registerUserDTO.getRole());
    }

}
