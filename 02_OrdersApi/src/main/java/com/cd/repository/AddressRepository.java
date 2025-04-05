package com.cd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd.bindings.AddressDTO;
import com.cd.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Address save(AddressDTO address);
}
