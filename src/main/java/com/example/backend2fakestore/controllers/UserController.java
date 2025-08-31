package com.example.backend2fakestore.controllers;

import com.example.backend2fakestore.models.AppUser;
import com.example.backend2fakestore.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model){
        String error = userService.registerUser(username, password);
        if (error != null){
            model.addAttribute("error", error);
            return "register";
        }
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        String error = userService.loginUser(username, password);
        if (error != null){
            model.addAttribute("error", error);
            return "login";
        }

    AppUser user = userService.findByUsername(username).orElseThrow();
    session.setAttribute("username", username);
    session.setAttribute("role", user.getRole());
        return "redirect:/home";

    }
    @GetMapping("/register")
    public String registerForm(){
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

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
