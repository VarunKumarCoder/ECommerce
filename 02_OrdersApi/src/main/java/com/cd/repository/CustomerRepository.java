package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd.bindings.CustomerDTO;
import com.cd.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDTO, Long>{

	public Customer findByEmail(String Email);

	public void save(Customer customer);
	
}
