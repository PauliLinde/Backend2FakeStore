package com.example.backend2fakestore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FakestoreTestIT {

    @Test
    void shouldReturnAttribute (){
    String url = "https://fakestoreapi.com/products";
    TestRestTemplate rt = new TestRestTemplate();
    String json = rt.getForObject(url, String.class);
    assertNotNull(json.contains("title"));

    }

}
