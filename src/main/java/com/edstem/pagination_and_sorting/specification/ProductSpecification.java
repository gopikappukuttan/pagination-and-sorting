package com.edstem.pagination_and_sorting.specification;

import com.edstem.pagination_and_sorting.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

	public static Specification<Product> hasCategory(String category) {
		return ((root, query, criteriaBuilder) -> category == null ? null : criteriaBuilder.equal(root.get("category"), category));
	}

	public static Specification<Product> hasMinPrice(Double minPrice) {
		return ((root, query, criteriaBuilder) -> minPrice == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
	}

	public static Specification<Product> hasMaxPrice(Double maxPrice) {
		return ((root, query, criteriaBuilder) -> maxPrice == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
	}
}
