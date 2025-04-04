package com.cd.bindings;

import java.util.List;

import lombok.Data;

@Data
public class PurchageDTO {
	
	private CustomerDTO customer;
	private AddressDTO address;
	private OrderDto order;
	private List<OrderItemDTO> orderItems;
	
}
