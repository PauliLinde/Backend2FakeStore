package com.example.backend2fakestore;

import com.example.backend2fakestore.models.Root;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest

class Backend2FakeStoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test //obs denna funkar ej pga ej konfigurerat tester
    void testJSONMapper() throws Exception {
        String JSON = "[{\"id\":1,\"title\":\"Test Product\",\"price\":10.99,\"description\":\"Test desc\",\"category\":\"electronics\",\"image\":\"url\",\"rating\":{\"rate\":4.5,\"count\":120}}]";
   ObjectMapper mapper = new ObjectMapper();
        Root[] roots = mapper.readValue(JSON, Root[].class);

        assertEquals(1, roots.length);
        assertEquals("Test Product", roots[0].getTitle());
        assertEquals(4.5, roots[0].getRating().getRate());
    }


}
