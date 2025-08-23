package com.example.backend2fakestore.services;
import com.example.backend2fakestore.models.Root;
import com.example.backend2fakestore.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
//@RequiredArgsConstructor
@Component
public class FakeStoreService {

	ProductRepository pRepository;

	public FakeStoreService(ProductRepository pRepository) {
		this.pRepository = pRepository;
	}

			public void getItemsAndSave() throws IOException {
			ObjectMapper mapper = new ObjectMapper();
			URL url = new URL("https://fakestoreapi.com/products");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				StringBuilder SBuilder = new StringBuilder();
				String line;
				while ((line = in.readLine()) != null) {
					SBuilder.append(line);
				}

				Root[] fakeProducts = mapper.readValue(SBuilder.toString(), Root[].class);

				for (Root root : fakeProducts) {
					pRepository.save(root);
					System.out.println("Saved product: " + root.getTitle());
				}
			}
		}
	}


