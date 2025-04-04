package com.cd.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 35,nullable = false)
	private String name;
	@Column(length = 50,nullable = false)
	private String title;
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private Boolean active;
	
	

}
