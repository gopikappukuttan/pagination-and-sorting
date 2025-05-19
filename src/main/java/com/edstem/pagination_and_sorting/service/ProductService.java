package com.edstem.pagination_and_sorting.service;

import com.edstem.pagination_and_sorting.contract.PageableResponse;
import com.edstem.pagination_and_sorting.model.Product;
import com.edstem.pagination_and_sorting.repository.ProductRepository;
import com.edstem.pagination_and_sorting.specification.ProductSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public PageableResponse<Product> getAllProducts(
			int page,
			int size,
			String sortBy,
			String direction,
			String category,
			Double minPrice,
			Double maxPrice) {

		Sort sort = direction.equalsIgnoreCase("desc") ?
				Sort.by(sortBy).descending() :
				Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		Specification<Product> spec = Specification
				.where(ProductSpecification.hasCategory(category))
				.and(ProductSpecification.hasMinPrice(minPrice))
				.and(ProductSpecification.hasMaxPrice(maxPrice));

		Page<Product> productPage = productRepository.findAll(spec, pageable);

		PageableResponse<Product> response = new PageableResponse<>();
		response.setContent(productPage.getContent());
		response.setPageNumber(productPage.getNumber());
		response.setPageSize(productPage.getSize());
		response.setTotalElements(productPage.getTotalElements());
		response.setTotalPages(productPage.getTotalPages());
		response.setLastPage(productPage.isLast());
		return response;
	}

	public Slice<Product> getProductSlice(
			int page, int size, String category, Double minPrice, Double maxPrice) {

		Pageable pageable = PageRequest.of(page, size);

		Specification<Product> spec = Specification
				.where(ProductSpecification.hasCategory(category))
				.and(ProductSpecification.hasMinPrice(minPrice))
				.and(ProductSpecification.hasMaxPrice(maxPrice));

		return productRepository.findAll(spec, pageable);
	}

}
