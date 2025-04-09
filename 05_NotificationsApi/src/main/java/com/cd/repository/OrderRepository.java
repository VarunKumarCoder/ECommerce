package com.cd.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cd.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
	
	@Query("Select o From Order o where o.siderStatus= :status")
	List<Order> findByStatus(String status);
	
	@Query("Select o From Order o Where o.orderStatus!= :status")
	List<Order> findByStatusNot(String status);
	
	@Query("Select o From Order o Where o.deliveryDate= :deliveryDate")
	List<Order> findByOrderDeliveryDate(LocalDate deliveryDate);

}
