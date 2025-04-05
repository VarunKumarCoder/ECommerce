package com.cd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Order extends JpaRepository<Order, Long> {

	public Order findByRazorPayOrderId(String razorPayOrderId);
	public List<Order> findByEmail(String email);
}
