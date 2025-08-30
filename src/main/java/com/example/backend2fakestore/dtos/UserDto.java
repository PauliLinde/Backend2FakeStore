package com.example.backend2fakestore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String username;
    private String password;
    private String role;

}
