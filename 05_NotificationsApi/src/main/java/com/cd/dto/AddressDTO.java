package com.cd.dto;

import com.cd.entity.Customer;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddressDTO {

	private String houseno;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	@ManyToOne
	private Customer customer;
}
