package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.dtos.RegisterUserDTO;
import com.example.backend2fakestore.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserDTO newUser,
                           Model model){
        String error = authService.registerUser(newUser);
        if (error != null){
            model.addAttribute("error", error);
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("registerUserDTO", new RegisterUserDTO());
        return "register";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

}
