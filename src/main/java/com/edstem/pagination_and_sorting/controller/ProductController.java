package com.edstem.pagination_and_sorting.controller;

import com.edstem.pagination_and_sorting.contract.PageableResponse;
import com.edstem.pagination_and_sorting.model.Product;
import com.edstem.pagination_and_sorting.service.ProductService;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public PageableResponse<Product> getProducts(@RequestParam(defaultValue = "0") int page,
												 @RequestParam(defaultValue = "10") int size,
												 @RequestParam(defaultValue = "id") String sortBy,
												 @RequestParam(defaultValue = "asc") String direction,
												 @RequestParam(required = false) String category,
												 @RequestParam(required = false) Double minPrice,
												 @RequestParam(required = false) Double maxPrice) {

		return productService.getAllProducts(page, size, sortBy, direction, category, minPrice, maxPrice);
	}

	@GetMapping("/slice")
	public Slice<Product> getProductSlice(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice) {

		return productService.getProductSlice(page, size, category, minPrice, maxPrice);
	}

}
