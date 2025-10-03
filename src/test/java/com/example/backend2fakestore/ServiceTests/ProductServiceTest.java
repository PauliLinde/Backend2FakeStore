package com.example.backend2fakestore.ServiceTests;

import com.example.backend2fakestore.dtos.DisplayProductDTO;
import com.example.backend2fakestore.mappers.ProductMapper;
import com.example.backend2fakestore.models.Product;
import com.example.backend2fakestore.repository.ProductRepository;
import com.example.backend2fakestore.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({ProductService.class, ProductMapper.class})
public class ProductServiceTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	private Product testProduct;

	@BeforeEach
	void setUp(){
		Product.Rating rating = new Product.Rating(4.5, 100);

		testProduct = new Product();
		testProduct.setId(2);
		testProduct.setTitle("Test Backpack");
		testProduct.setPrice(599.00);
		testProduct.setDescription("A backpack to put all your tests into");
		testProduct.setCategory("Items");
		testProduct.setImage("https://fakestoreapi/backpack.jpg");
		testProduct.setRating(rating);

		entityManager.persist(testProduct);
		entityManager.flush();
	}

	@Test
	void getAllProductsTest(){
		List<DisplayProductDTO> allProducts = productService.getAllProducts();

		assertTrue(allProducts.size() > 0);
	}

	@Test
	void testFindProductAndSave() {
		Optional<Product> findProductById = productRepository.findById(2);

		assertThat(findProductById).isPresent();
		assertThat(findProductById.get().getTitle()).isEqualTo("Test Backpack");
		assertThat(findProductById.get().getPrice()).isEqualTo(599.00);
		assertThat(findProductById.get().getCategory()).isEqualTo("Items");
	}
	@Test
	void testRatingRange(){
		Product product = productRepository.findById(2).orElseThrow();

		assertThat(product.getRating()).isNotNull();
		assertThat(product.getRating().getRate()).isEqualTo(4.5);
		assertThat(product.getRating().getCount()).isEqualTo(100);
	}

	@Test
	void testLongDescription() {
		String longDescription = "LONG".repeat(2000);
		testProduct.setDescription(longDescription);
		productRepository.save(testProduct);

		Product findProductById = productRepository.findById(2).orElseThrow();
		assertThat(findProductById.getDescription()).hasSize(8000);

	}
	@Test
	void testOneProduct(){
		List<Product> products = productRepository.findAll();
		assertThat(products).hasSize(1);
	}
}
