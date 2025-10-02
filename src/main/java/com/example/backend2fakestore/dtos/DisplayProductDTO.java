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

    public DisplayProductDTO() {}

    public DisplayProductDTO(int id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
