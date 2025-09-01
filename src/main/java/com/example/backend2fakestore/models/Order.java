package com.example.backend2fakestore.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Order {

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public AppUser getAppUser() { return appUser; }
    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}



