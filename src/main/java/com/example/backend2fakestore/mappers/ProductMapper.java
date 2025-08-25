package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.ProductDto;
import com.example.backend2fakestore.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDto toDto(Product product) {
        if (product == null) return null;
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public Product toEntity(ProductDto productDto) {
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

    public List<ProductDto> toDtoList(List<Product> products){
        if (products == null) return null;
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Product> toEntityList(List<ProductDto> productDtos){
        if (productDtos == null) return null;
        return productDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
