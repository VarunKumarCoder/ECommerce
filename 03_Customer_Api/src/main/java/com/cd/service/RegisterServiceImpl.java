package com.cd.service;

import org.springframework.stereotype.Service;

import com.cd.binding.Register;
import com.cd.entity.Customer;
import com.cd.repo.CustomerRepo;

@Service
public class RegisterServiceImpl implements RegiterService{

	private CustomerRepo customerRepo;
	public RegisterServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	Customer customer=new Customer();
	
	@Override
	public Boolean register(Register register) {
		customer.setName(register.getName());
		customer.setEmail(register.getEmail());
		customer.setPassword(register.getPassword());
		customer.setPhoneNo(register.getPassword());
		customerRepo.save(customer);		
		return true;
	}

}
