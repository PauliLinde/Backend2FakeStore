package com.example.backend2fakestore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

}
