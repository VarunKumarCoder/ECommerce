package com.cd.dto;

import java.util.List;

import com.cd.entity.Address;
import com.cd.entity.OrderItem;

import lombok.Data;

@Data
public class OrderDTO {

	private int totalQuantity;
	private double totalPrice;
	private String razorPayOrderId;
	private String orderStatus;
	private String razorpayPaymentId;
	
	private Customer customer;
	
	private Address address;
	
	private List<OrderItem> orderItems;
}
