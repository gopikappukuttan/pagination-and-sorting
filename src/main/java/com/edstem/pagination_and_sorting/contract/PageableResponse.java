package com.edstem.pagination_and_sorting.contract;

import lombok.Data;

import java.util.List;

@Data
public class PageableResponse<T> {
	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean isLastPage;
}
