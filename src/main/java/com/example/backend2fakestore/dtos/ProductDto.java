package com.example.backend2fakestore.dtos;

import lombok.*;

@Getter
@Setter
public class ProductDto {

    public int id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
}
