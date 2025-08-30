package com.example.backend2fakestore.mappers;

import com.example.backend2fakestore.dtos.UserDto;
import com.example.backend2fakestore.models.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto toDto(AppUser appUser){
        if (appUser == null) return null;
        UserDto userDto = new UserDto();
        userDto.setUsername(appUser.getUsername());
        userDto.setPassword(appUser.getPassword());
        userDto.setRole(appUser.getRole());
        return userDto;
    }

    public AppUser toEntity(UserDto userDto){
        if (userDto == null) return null;
        AppUser appUser = new AppUser();
        appUser.setUsername(userDto.getUsername());
        appUser.setPassword(userDto.getPassword());
        appUser.setRole(userDto.getRole());
        return appUser;
    }

    public List<UserDto> toDtoList(List<AppUser> appUsers){
        if (appUsers == null) return null;
        return appUsers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<AppUser> toEntityList(List<UserDto> userDtos){
        if (userDtos == null) return null;
        return userDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
