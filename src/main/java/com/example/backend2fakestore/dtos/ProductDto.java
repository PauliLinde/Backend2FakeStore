package com.example.backend2fakestore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    public int id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;

}
