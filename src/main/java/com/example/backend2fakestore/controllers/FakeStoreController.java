package com.example.backend2fakestore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class FakeStoreController {

    @GetMapping("/")
    public String index(Model model){
        String username = "Steve";
        if (username != null){
            model.addAttribute("username", username);
        }
        return "index";
    }

    @GetMapping("/api/fetch")
    @ResponseBody
    public String fetchAllItems(Model model) throws IOException {
        URL url = new URL("https://fakestoreapi.com/products/1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            jsonResponse.append(line);
        }
        in.close();
        return jsonResponse.toString();
    }
}
