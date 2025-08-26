package com.example.backend2fakestore.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	public double rate;
	public int count;
}
