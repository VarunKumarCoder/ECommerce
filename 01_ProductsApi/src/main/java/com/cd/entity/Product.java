package com.cd.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Represents a Product entity in the E-Commerce system.
 */
@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 35, nullable = false)
    @NotBlank(message = "Product name must not be blank")
    @Size(max = 35, message = "Product name must be at most 35 characters")
    private String name;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Title must not be blank")
    @Size(max = 50, message = "Title must be at most 50 characters")
    private String title;

    @NonNull
    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid unit price format")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Size(max = 255, message = "Image URL must be at most 255 characters")
    private String imageUrl;

    @NotNull(message = "Active status must not be null")
    @Column(nullable = false)
    private Boolean active = true;

    @NotNull(message = "Units in stock must not be null")
    @Min(value = 0, message = "Units in stock must be 0 or more")
    @Column(name = "units_in_stock", nullable = false)
    private Integer unitsInstock;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Product category is required")
    private ProductCategory category;
}
