package com.example.backend2fakestore.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;

    @ManyToOne /*The @OneToOne annotation creates a unique constraint, meaning each product can only appear in one order ever. This is why you get "Duplicate entry '1'" - you're trying to create a second order for product with ID 1.*/
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}

