package com.cd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cd.bindings.ReportDTO;
import com.cd.entity.Order;
import com.cd.repository.OrderRepo;
import com.cd.specification.ReportSpecificatoin;

@Service
public class ReportService {

	@Autowired
	private OrderRepo orderRepo;
	
	public List<Order> filterOrders(ReportDTO reportDTO){
		Specification<Order> specification=Specification
				.where(ReportSpecificatoin.hasCustomerEmail(reportDTO.getCustomerEmail()))
				.and(ReportSpecificatoin.hasStartDate(reportDTO.getStartDate()))
				.and(ReportSpecificatoin.hasEndDate(reportDTO.getEndDate()));
		return orderRepo.findAll(specification);
	}
}
