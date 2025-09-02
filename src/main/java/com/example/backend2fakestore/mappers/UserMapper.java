package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.RegisterDto;
import com.example.backend2fakestore.models.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public AppUser registerDtoToAppUser(RegisterDto registerDto){
        if (registerDto == null) return null;
        AppUser appUser = new AppUser();
        appUser.setUsername(registerDto.getUsername());
        appUser.setPassword(registerDto.getPassword());
        appUser.setRole(registerDto.getRole());
        return appUser;
    }
}
