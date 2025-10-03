package com.example.backend2fakestore.dtos;

import lombok.Data;

@Data
public class DisplayProductDTO {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
