package com.example.backend2fakestore.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public LocalDate date;
    public int total;
    public int quantity;

    @ManyToOne /*The @OneToOne annotation creates a unique constraint, meaning each product can only appear in one order ever. This is why you get "Duplicate entry '1'" - you're trying to create a second order for product with ID 1.*/
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public AppUser appUser;
}

