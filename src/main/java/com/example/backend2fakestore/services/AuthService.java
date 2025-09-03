package com.example.backend2fakestore.services;

import com.example.backend2fakestore.dtos.RegisterUserDTO;
import com.example.backend2fakestore.mappers.UserMapper;
import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public String registerUser(RegisterUserDTO registerUserDTO) {
        if (existsByUsername(registerUserDTO.getUsername())) {
            return "Username already exists";
        }
        if (!isValidPassword(registerUserDTO.getPassword())) {
            return "Password need to be at least 5 characters and contain a digit.";
        }

        AppUser newUser = userMapper.registerUserDTOToAppUser(registerUserDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("ROLE_" + newUser.getRole());
        userRepository.save(newUser);
        return null;
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{5,}$");
    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
