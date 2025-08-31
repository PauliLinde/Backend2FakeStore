package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder; // now using Spring bean

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String registerUser(String username, String password) {
        if (findByUsername(username).isPresent()) {
            return "Username already exists";
        }
        if (!isValidPassword(password)) {
            return "Password need to be at least 5 characters and contain a digit.";
        }
        String hashedPassword = encoder.encode(password);
        saveUser(username, hashedPassword, "ADMIN"); // # TODO få in värdet via form istället
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
