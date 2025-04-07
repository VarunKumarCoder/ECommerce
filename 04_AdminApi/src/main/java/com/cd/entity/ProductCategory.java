package com.cd.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="product_category")
public class ProductCategory {

	private Long id;
	
	private String categoryName;
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL)
	private Set<Product> products;
}
