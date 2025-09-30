package com.example.backend2fakestore;

import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.FakeStoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;
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
		//kopplar till Product, gör en mockprodukt.
		Product mProduct = new Product();
		mProduct.setId(1);
		mProduct.setTitle("Test Product");

		List<Product> mockList = Collections.singletonList(mProduct);

		when(pRepository.saveAll(anyList())).thenReturn(mockList);

		fStoreService.getItemsAndSave();

		verify(pRepository, atLeastOnce()).saveAll(anyList());
	}
}
