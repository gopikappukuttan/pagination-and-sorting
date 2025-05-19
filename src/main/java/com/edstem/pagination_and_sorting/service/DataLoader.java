package com.edstem.pagination_and_sorting.service;

import com.edstem.pagination_and_sorting.model.Product;
import com.edstem.pagination_and_sorting.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DataLoader implements CommandLineRunner {

	private final ProductRepository productRepository;

	public DataLoader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();
		String[] categories = {"Electronics", "Books", "Clothing", "Home", "Toys"};
		IntStream.range(1, 1000).forEach(i -> {
			Product p = Product.builder().name("Product " + i)
					.category(categories[random.nextInt(categories.length)])
					.price(10 + (1000 - 10) * random.nextDouble())
					.rating(1 + (5 - 1) * random.nextDouble())
					.build();
			productRepository.save(p);
		});

	}
}
