package com.example.backend2fakestore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private int id;
    private String title;
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;

    @Embedded
    private Rating rating;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rating {
        private double rate;
        private int count;
    }
}
