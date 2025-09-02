package com.example.backend2fakestore.services;

import com.example.backend2fakestore.dtos.RegisterDto;
import com.example.backend2fakestore.mappers.UserMapper;
import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder encoder,  UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    public String registerUser(RegisterDto registerDto) {
        if (findByUsername(registerDto.getUsername()).isPresent()) {
            return "Username already exists";
        }
        if (!isValidPassword(registerDto.getPassword())) {
            return "Password need to be at least 5 characters and contain a digit.";
        }
        AppUser user = userMapper.registerDtoToAppUser(registerDto);
        String hashedPassword = encoder.encode(user.getPassword());
        saveUser(user.getUsername(), hashedPassword, "ROLE_" + user.getRole());
        return null;
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{5,}$");
    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private void saveUser(String username, String hashedPassword, String role) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRole(role);
        userRepository.save(user);
    }

    public Optional<String> findByUsername(String username) {
        return userRepository.findByUsername(username).map(AppUser::getPassword);
    }
}
