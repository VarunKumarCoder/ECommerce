package com.cd.bindings;

import java.util.List;

import com.cd.entity.Address;
import com.cd.entity.Customer;
import com.cd.entity.OrderItem;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDto {
	
	private int totalquantity;
	private double totalprice;
	private String razorPayOrderId;
	private String orderStatus;
	private String razorPayPaymentId;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Address address;
	
	@ElementCollection
	private List<OrderItem> orderItems;
}
