package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd.bindings.OrderItemDTO;
import com.cd.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	OrderItem save(OrderItemDTO item);
	
}
