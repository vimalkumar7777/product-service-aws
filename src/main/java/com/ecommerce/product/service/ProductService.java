package com.ecommerce.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.product.request.ProductRequest;
import com.ecommerce.product.response.ProductResponse;

public interface ProductService {

	ProductResponse create(ProductRequest request);

	ProductResponse getById(Long id);

	Page<ProductResponse> getAll(Pageable pageable);

	ProductResponse update(Long id, ProductRequest request);

	String delete(Long id);
}
