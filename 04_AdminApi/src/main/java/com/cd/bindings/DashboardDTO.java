package com.cd.bindings;

import lombok.Data;

@Data
public class DashboardDTO {

	private Long customerCount;
	private Long ordersCount;
	private Double amountCollected;
	private Long productCount;
}
