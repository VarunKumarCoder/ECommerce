package com.cd.bindings;

import lombok.Data;

@Data
public class OrderResponse {

	private String razorpayOrderId;
	private String OrderStatus;
	private String orderTrackingNumber;
}
