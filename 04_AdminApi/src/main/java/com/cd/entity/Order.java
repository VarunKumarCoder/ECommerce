package com.cd.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String orderTrackingNum;
	private String razorPayOrderId;
	private String razorPayPaymentId;
	private String email;
	private String orderStatus;
	private Double totalPrice;
	private int totalQuantity;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	@CreationTimestamp
	@Column(name="date_created", updatable=false)
	private LocalDate dateCreated;
	@UpdateTimestamp
	@Column(name="last_updated", insertable =false)
	private LocalDate lastUpdated;
	
	
}
