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
            return "Username already exists";
        }
        if (!isValidPassword(password)) {
            return "Password need to be at least 5 characters and contain a digit.";
        }
        String hashedPassword = hashPassword(password);
        saveUser(username, hashedPassword);
        return null;
    }

    public String loginUser(String username, String password){
        Optional<String> registeredPassword = findByUsername(username);
        if (registeredPassword.isEmpty()) {
            return "Login failed";
        }
        if (!encoder.matches(password, registeredPassword.get())){
            return "Login failed";
        }
        return null;
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{5,}$");
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

    public Optional<String> findByUsername(String username) {
        return userRepository.findByUsername(username).map(AppUser::getPassword);
    }

}
