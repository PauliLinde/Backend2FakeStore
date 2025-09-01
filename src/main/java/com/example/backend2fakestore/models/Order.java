package com.example.backend2fakestore.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Order {

     @Id
     public int id;
     public LocalDateTime date;
     public int total;

    @OneToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public AppUser appuser;
}


