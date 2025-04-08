package com.cd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bindings.DashboardDTO;
import com.cd.bindings.ReportDTO;
import com.cd.entity.Order;
import com.cd.service.DashBoardService;
import com.cd.service.ReportService;

@RestController
@RequestMapping(value="/api/admin")
@CrossOrigin("http://localhost:4200/")
public class AdminController {
	
	@Autowired
	public DashBoardService dashboardservice;
	
	@Autowired
	public ReportService reportService;
	
	@GetMapping(value="/dashboard")
	public ResponseEntity<DashboardDTO> getDashboardData(){
		DashboardDTO dashboardDTO=dashboardservice.fetchDashBoardValues();
		return new ResponseEntity<>(dashboardDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/filter")
	public List<Order> filterOrders(@RequestBody ReportDTO reportDTO){
		return reportService.filterOrders(reportDTO);
	}

}
