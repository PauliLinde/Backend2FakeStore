package com.example.backend2fakestore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FakestoreTestIT {



    @Test
    void shouldReturnAttribute (){
    String url = "https://fakestoreapi.com/products";
    TestRestTemplate rt = new TestRestTemplate();
    String json = rt.getForObject(url, String.class);

    assertNotNull(json);

    assertTrue(json.contains("id"));
    assertTrue(json.contains("price"));
    assertTrue(json.contains("title"));
    assertTrue(json.contains("category"));
    assertTrue(json.contains("description"));
    assertTrue(json.contains("image"));
    assertTrue(json.contains("count"));
    assertTrue(json.contains("rating"));
    assertTrue(json.contains("rate"));

    }

}
