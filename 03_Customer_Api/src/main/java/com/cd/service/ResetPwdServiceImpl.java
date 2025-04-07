package com.cd.service;

import com.cd.binding.ResetPassword;
import com.cd.binding.RestResponse;
import com.cd.entity.Customer;
import com.cd.repo.CustomerRepo;

public class ResetPwdServiceImpl implements ResetPwdService {
	
	private CustomerRepo customerRepo;
	
	public ResetPwdServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	RestResponse response=new RestResponse();
	Customer customer=new Customer();


	@Override
	public String resetPassword(ResetPassword resetPassword) {
		String response;
		customer=customerRepo.findByEmail(resetPassword.getEmail());
		if(customer==null) {
			response="Please Enter Registered Email ID";
			return response;
		}
		if(!resetPassword.getNewPwd().equals(resetPassword.getConfirmNewPwd())) {
			response="Passwords Must Match";
			return response;
		}
		else {
			customer.setEmail(resetPassword.getEmail());
			customer.setPassword(resetPassword.getNewPwd());
			customerRepo.save(customer);
			response="Password Updated Please Try to login now";
			return response;
		}
		
	}

}
