package com.example.backend2fakestore;

import com.example.backend2fakestore.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest

class Backend2FakeStoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testJSONMapper() throws Exception { //testar mappern med en lokal JSON-sträng
        String JSON = "[{\"id\":1,\"title\":\"Test Product\",\"price\":10.99,\"description\":\"Test desc\",\"category\":\"electronics\",\"image\":\"url\",\"rating\":{\"rate\":4.5,\"count\":120}}]";
   ObjectMapper mapper = new ObjectMapper();
        Product[] roots = mapper.readValue(JSON, Product[].class);

        assertEquals(1, roots.length);
        assertEquals("Test Product", roots[0].getTitle());
        assertEquals(4.5, roots[0].getRating().getRate());
    }


}
