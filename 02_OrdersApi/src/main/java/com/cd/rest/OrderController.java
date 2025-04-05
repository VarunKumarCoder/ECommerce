package com.cd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bindings.OrderResponse;
import com.cd.bindings.PaymentCallBackDTO;
import com.cd.bindings.PurchageDTO;
import com.cd.entity.Order;
import com.cd.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value="/create-order", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<OrderResponse> createOrder(@RequestBody PurchageDTO purchageDto) throws Exception {
		OrderResponse response=orderService.createOrder(purchageDto);
		return new ResponseEntity<OrderResponse>(response, HttpStatus.OK);
	}
	
	 @PostMapping("/payment-verification")
	    public boolean verifyPayment(@RequestBody PaymentCallBackDTO paymentCallbackDTO, Model model) {
		System.out.println("Payload is :" + paymentCallbackDTO.getRazorpayOrderId() +" ," + paymentCallbackDTO.getRazorpayPaymentId()
	    + "," + paymentCallbackDTO.getRazorpaySignature());
		boolean isPaymentConfirmed = orderService.verifyPaymentAndUpdateOrderStatus(paymentCallbackDTO);
		//model.addAttribute("order", updatedOrder);
		return isPaymentConfirmed;
	    }
	 
	 
		/*
		 * @GetMapping("getOrderDetails/{email}") public List<Order>
		 * getOrderByEmail(@PathVariable String email){ return
		 * orderService.getOrderDetails(email); }
		 */
	 
	 @GetMapping("getOrderDetails/{email}")
	 public ResponseEntity<List<Order>> getOrderByEmail(@PathVariable String email) {
	     List<Order> orders = orderService.getOrderDetails(email);

	     if (orders.isEmpty()) {
	         return ResponseEntity.noContent().build(); // or return 404 if you prefer
	     } else {
	         return ResponseEntity.ok(orders);
	     }
	 }
	
}
