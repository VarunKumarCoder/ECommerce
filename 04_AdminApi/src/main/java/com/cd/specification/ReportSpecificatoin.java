package com.cd.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.cd.entity.Order;

public class ReportSpecificatoin {
	
	public static Specification<Order> hasCustomerEmail(String cutomerEmail){
		return (root,query,criteriaBuilder)->{
			if(cutomerEmail==null || cutomerEmail.isEmpty()) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("customer").get("email"), cutomerEmail);
		};
	}
	
	public static Specification<Order> hasStartDate(LocalDate startDate){
		return (root,query,criteriaBuilder) ->{
			if(startDate==null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), startDate);
		};
	}
	
	public static Specification<Order> hasEndDate(LocalDate enddate){
		return (root,query,criteriaBuilder) ->{
			if(enddate==null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), enddate);
		};
	}
}
