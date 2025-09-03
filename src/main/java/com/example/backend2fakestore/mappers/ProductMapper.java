package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.ProductDTO;
import com.example.backend2fakestore.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        if (product == null) return null;
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public Product toEntity(ProductDTO productDto) {
        if (productDto == null) return null;
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setImage(productDto.getImage());
        return product;
    }

    public List<ProductDTO> toDtoList(List<Product> products){
        if (products == null) return null;
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Product> toEntityList(List<ProductDTO> productDTOS){
        if (productDTOS == null) return null;
        return productDTOS.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
