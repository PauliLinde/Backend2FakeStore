package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute AppUser appUser,
                           Model model){
        String error = userService.registerUser2(appUser);
        if (error != null){
            model.addAttribute("error", error);
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
