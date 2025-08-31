package com.example.backend2fakestore.services;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        int saltStrength = 10;
        BCryptPasswordEncoder.BCryptVersion bCryptVersion = BCryptPasswordEncoder.BCryptVersion.$2B;
        this.encoder = new BCryptPasswordEncoder(bCryptVersion, saltStrength);
    }

    public String registerUser(String username, String password) {
        if (findByUsername(username).isPresent()) {
            return "Username already exists" + username + password;
        }
        if (!isValidPassword(password)) {
            return "Password need to be at least 5 characters and contain a digit.";
        }
        String hashedPassword = hashPassword(password);
        saveUser(username, hashedPassword);
        return null;
    }

    public String loginUser(String username, String password){
        Optional<AppUser> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return "Login failed";
        }
        AppUser user = userOpt.get();
        if (!encoder.matches(password, user.getPassword())) {
            return "Login failed";
        }
        System.out.println("return null");
        return null;
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{2,}$");
    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private String hashPassword(String password) {
        return encoder.encode(password);
    }

    private void saveUser(String username, String hashedPassword) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
