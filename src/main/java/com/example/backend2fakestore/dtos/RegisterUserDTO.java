package com.example.backend2fakestore.dtos;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String username;
    private String password;
    private String role;
}
