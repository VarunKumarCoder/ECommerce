package com.cd.service;

import org.springframework.stereotype.Service;

import com.cd.binding.Login;
import com.cd.entity.Customer;
import com.cd.repo.CustomerRepo;

@Service
public class LoginServiceImpl implements LoginService {
	
	private CustomerRepo customerRepo;
	
	public LoginServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}



	@Override
	public Login loginHandle(Login login) {
		Customer customer=customerRepo.findByEmailPassword(login.getEmail(),login.getPassword());
		Login login1=new Login();
		if(customer!=null) {
			login1.setEmail(customer.getEmail());
			login1.setPassword(customer.getPassword());
			return login1;
		}
		return login1;
	}

}
