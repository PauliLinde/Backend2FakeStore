package com.example.backend2fakestore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     public int id;
     public LocalDateTime date;
     public int total;

    @OneToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public AppUser appUser;
}



