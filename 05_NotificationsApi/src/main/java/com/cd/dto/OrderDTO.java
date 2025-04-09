package com.cd.dto;

import java.util.List;

import com.cd.entity.Address;
import com.cd.entity.Customer;
import com.cd.entity.OrderItem;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDTO {

	private int totalQuantity;
	private double totalPrice;
	private String razorPayOrderId;
	private String orderStatus;
	private String razorpayPaymentId;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Address address;
	
	@ElementCollection
	private List<OrderItem> orderItems;
}
