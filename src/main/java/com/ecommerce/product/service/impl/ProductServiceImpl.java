package com.ecommerce.product.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.request.ProductRequest;
import com.ecommerce.product.response.ProductResponse;
import com.ecommerce.product.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ModelMapper mapper;
	
	

	@Transactional
	public ProductResponse create(ProductRequest request) {

		log.info("Creating product with name: {}, price: {}", request.getName(), request.getPrice());

		Product product = mapper.map(request, Product.class);

		Product savedProduct = repository.save(product);

		log.info("Product created successfully with id: {}", savedProduct.getId());
		

		return mapper.map(savedProduct, ProductResponse.class);
	}

	public ProductResponse getById(Long id) {

		Product product = repository.findById(id).orElseThrow(() -> {
			log.warn("Product not found with id: {}", id);
			return new ProductNotFoundException("Product not found with id: " + id);
		});

		log.info("Product found with id: {}, name: {}", product.getId(), product.getName());

		return mapper.map(product, ProductResponse.class);
	}

	public Page<ProductResponse> getAll(Pageable pageable) {

		
		Page<ProductResponse> responsePage = repository.findAll(pageable)
				.map(product -> mapper.map(product, ProductResponse.class));

		log.info("Products fetched successfully - totalElements: {}, totalPages: {}", responsePage.getTotalElements(),
				responsePage.getTotalPages());

		return responsePage;
	}

	@Transactional
	public ProductResponse update(Long id, ProductRequest request) {

		Product product = repository.findById(id).orElseThrow(() -> {
			log.warn("Product not found for update with id: ", id);
			return new ProductNotFoundException("Product not found with id: " + id);
		});

		mapper.map(request, product);
		Product updatedProduct = repository.save(product);
		log.info("Product updated successfully with id:", updatedProduct.getId());
		return mapper.map(updatedProduct, ProductResponse.class);
	}

	@Transactional
	public String delete(Long id) {

		if (!repository.existsById(id)) {
			log.error("Product not found with id : ", id);
			throw new ProductNotFoundException("Product not found with id: " + id);
		}

		repository.deleteById(id);
		return "Product deleted successfully with id: " + id;
	}

}
