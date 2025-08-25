package com.example.backend2fakestore;

import com.example.backend2fakestore.models.Root;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FakeStoreServiceTest {

	@Mock
	private ProductRepository pRepository;

	@InjectMocks
	private FakeStoreService fStoreService;

	@Test
	void testGetItemsAndSave() throws IOException {

		Root mockProduct = new Root();
		mockProduct.id = 1;
		mockProduct.title = "Test Product";

		when(pRepository.save(any(Root.class))).thenReturn(mockProduct);

		fStoreService.getItemsAndSave();

		verify(pRepository, atLeastOnce()).save(any(Root.class));
	}
}
