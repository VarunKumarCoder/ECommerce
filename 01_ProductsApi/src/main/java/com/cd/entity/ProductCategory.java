package com.cd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents a product category in the E-Commerce system.
 */
@Entity
@Table(name = "product_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Category name must not be blank")
    @Size(max = 50, message = "Category name must be at most 50 characters")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Product> products;
}
