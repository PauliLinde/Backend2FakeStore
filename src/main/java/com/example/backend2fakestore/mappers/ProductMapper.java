package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.DisplayProductDTO;
import com.example.backend2fakestore.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public DisplayProductDTO productToDisplayProductDTO(Product product) {
        if (product == null) return null;
        DisplayProductDTO displayProductDTO = new DisplayProductDTO();
        displayProductDTO.setId(product.getId());
        displayProductDTO.setTitle(product.getTitle());
        displayProductDTO.setPrice(product.getPrice());
        displayProductDTO.setDescription(product.getDescription());
        displayProductDTO.setCategory(product.getCategory());
        displayProductDTO.setImage(product.getImage());
        return displayProductDTO;
    }

    public List<DisplayProductDTO> productListToDisplayProductDTOList(List<Product> products) {
        return products.stream()
                .map(this::productToDisplayProductDTO)
                .collect(Collectors.toList());
    }

}
