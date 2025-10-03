package com.example.backend2fakestore.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminOrderListDTO {

    private int id;
    private LocalDate date;
    private String productTitle;
    private String username;

}
