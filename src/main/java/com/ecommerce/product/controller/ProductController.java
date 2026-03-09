package com.ecommerce.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.request.ProductRequest;
import com.ecommerce.product.response.ProductResponse;
import com.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
    private ProductService service;
	
	

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@Positive(message = "Product id must be greater than zero") @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
    		@Positive(message = "Product id must be greater than zero") @PathVariable Long id,
           @Valid @RequestBody ProductRequest request) {
    	return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Positive(message = "Product id must be greater than zero") @PathVariable Long id) {
    	return ResponseEntity.ok(service.delete(id));
    }
}
