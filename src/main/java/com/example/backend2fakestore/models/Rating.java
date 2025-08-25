package com.example.backend2fakestore.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Rating {
	public double rate;
	public int count;
}
