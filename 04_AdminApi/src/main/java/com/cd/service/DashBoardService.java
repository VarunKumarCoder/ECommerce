package com.cd.service;

import org.springframework.stereotype.Service;

import com.cd.bindings.DashboardDTO;
import com.cd.repository.CustomerRepo;
import com.cd.repository.OrderRepo;
import com.cd.repository.ProductRepo;

@Service
public class DashBoardService {

	private CustomerRepo customerRepo;
	private OrderRepo orderRepo;
	private ProductRepo productRepo;
	public DashBoardService(CustomerRepo customerRepo, OrderRepo orderRepo, ProductRepo productRepo) {
		this.customerRepo = customerRepo;
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
	}
	
	public DashboardDTO fetchDashBoardValues() {
		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setProductCount(productRepo.count());
		dashboardDTO.setCustomerCount(customerRepo.count());
		dashboardDTO.setAmountCollected(orderRepo.findTotalAmount());
		dashboardDTO.setOrdersCount(orderRepo.count());
		return dashboardDTO;
	}
	
}
