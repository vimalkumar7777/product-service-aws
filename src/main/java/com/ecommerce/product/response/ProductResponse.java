package com.ecommerce.product.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	private Long id;
	private String name;
	private double price;
	private String description;
	private String category;
	private int stock;
}
