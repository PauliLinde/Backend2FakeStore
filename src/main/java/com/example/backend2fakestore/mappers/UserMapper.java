package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.RegisterUserDTO;
import com.example.backend2fakestore.models.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AppUser registerUserDTOToAppUser(RegisterUserDTO registerUserDTO){
        if (registerUserDTO == null) return null;
        AppUser appUser = new AppUser();
        appUser.setUsername(registerUserDTO.getUsername());
        appUser.setPassword(registerUserDTO.getPassword());
        appUser.setRole(registerUserDTO.getRole());
        return appUser;
    }

}
