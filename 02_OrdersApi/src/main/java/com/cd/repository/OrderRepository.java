package com.cd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	public Order findByRazorPayOrderId(String razorPayOrderId);
	public List<Order> findByEmail(String email);
	public void save(com.razorpay.Order order);
}
