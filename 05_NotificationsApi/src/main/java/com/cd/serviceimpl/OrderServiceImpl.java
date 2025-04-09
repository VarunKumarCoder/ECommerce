package com.cd.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.cd.constants.OrderStatus;
import com.cd.entity.Order;
import com.cd.repository.OrderRepository;
import com.cd.service.OrderService;

public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepo;
	private LocalDate today=LocalDate.now();
	
	@Override
	public Order updateOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public void deleteInvoiceFromLocalStorage(String orderTrackingNumber) {
		try {
			Files.deleteIfExists(Paths.get(getInvoiceName(orderTrackingNumber)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Order> getOrdersEligibilityForDelivery() {
		return orderRepo.findByOrderDeliveryDate(today);
	}

	@Override
	public List<Order> getUnconfirmedOrders() {
		return orderRepo.findByStatusNot(OrderStatus.CONFIRMED.name());
	}

	@Override
	public String getInvoiceName(String orderTrackingNumber) {		
		return "invoices/invoice"+"-"+orderTrackingNumber+".pdf";
	}

}
