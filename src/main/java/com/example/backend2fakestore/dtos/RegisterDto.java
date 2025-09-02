package com.example.backend2fakestore.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String role;
}
