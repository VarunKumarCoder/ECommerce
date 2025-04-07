package com.cd.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String title;
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private Boolean active;
	
	private int unitsInStock;
	
	private Date dateCreated;
	
	private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable =false)
	private ProductCategory category;
	
	
	
}
