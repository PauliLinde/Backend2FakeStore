package com.example.backend2fakestore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

	@Id
	public int id;
	public String title;
	public double price;

	@Column(columnDefinition = "TEXT")
	public String description;
	public String category;
	public String image;

	@Embedded
	public Rating rating;
}
