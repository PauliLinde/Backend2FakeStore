package com.example.backend2fakestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@GetMapping("/shop/{id}")
	public String shopProduct(@PathVariable int id) {
		return "The product with id " + id + " has been bought";
	}
}
