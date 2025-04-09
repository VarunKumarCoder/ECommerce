package com.cd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cd.entity.Order;

@Service
public interface OrderService {

	public Order updateOrder(Order order);
	
	public void deleteInvoiceFromLocalStorage(String orderTrackingNumber);
	
	public List<Order> getOrdersEligibilityForDelivery();
	
	public List<Order> getUnconfirmedOrders();
	
	public String getInvoiceName(String orderTrackingNumber);
}
