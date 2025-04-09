package com.cd.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cd.dto.OrderDTO;
import com.cd.entity.Order;

@Component
public class OrderMapper {

	@Autowired
	private static ModelMapper modelMapper;
	
	public static OrderDTO convertToDto(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public static Order convertToDto(OrderDTO orderDto) {
		return modelMapper.map(orderDto, Order.class);
	}
}
