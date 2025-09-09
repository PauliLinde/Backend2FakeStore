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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FakeStoreServiceTest {


	//skapar upp en lokal pRepository, falsk version
	@Mock
	private ProductRepository pRepository;

	//skapar en lokal fStoreService och injicerar pRepository automatiskt
	@InjectMocks
	private FakeStoreService fStoreService;

	//testet anropar API:et
	@Test
	void testGetItemsAndSave() throws IOException {
		//kopplar till Product, gör en mockprodukt.
		Product mProduct = new Product();
		mProduct.setId(1);
		mProduct.setTitle("Test Product");

		//när någon anropar save() med vilken Product som helst, returnera mockProduct
		when(pRepository.save(any(Product.class))).thenReturn(mProduct);

        //utför koden som ska testas
		fStoreService.getItemsAndSave();

        //testar att save() anropades
		verify(pRepository, atLeastOnce()).save(any(Product.class));
	}
}
