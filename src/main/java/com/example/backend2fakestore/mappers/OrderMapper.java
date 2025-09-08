package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.AdminOrderListDTO;
import com.example.backend2fakestore.models.ProductOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public AdminOrderListDTO productOrderToAdminOrderListDTO(ProductOrder productOrder){
        if (productOrder == null) return null;
        AdminOrderListDTO adminOrderListDTO = new AdminOrderListDTO();
        adminOrderListDTO.setId(productOrder.getId());
        adminOrderListDTO.setDate(productOrder.getDate());
        adminOrderListDTO.setProductTitle(productOrder.getProduct().getTitle());
        adminOrderListDTO.setUsername(productOrder.getAppUser().getUsername());
        return adminOrderListDTO;
    }

    public List<AdminOrderListDTO> productOrderListToAdminOrderDTOList(List<ProductOrder> productOrders){
        return productOrders.stream()
                .map(this::productOrderToAdminOrderListDTO)
                .collect(Collectors.toList());
    }
}
