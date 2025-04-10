package com.cd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cd.entity.Order;
import com.cd.service.WatiService;

@Component
public class PaymentRemainderUtility {

	@Autowired
	private WatiService watiService;


	public void sendPaymentRemainder(Order order) {
		watiService.sendPaymentRemainder(order.getCustomer().getPno(), order.getCustomer().getName(), order.getOrderTrackingNum());
	}
}
