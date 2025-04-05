package com.cd.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cd.bindings.OrderItemDTO;
import com.cd.bindings.OrderResponse;
import com.cd.bindings.PaymentCallBackDTO;
import com.cd.bindings.PurchageDTO;
import com.cd.entity.Address;
import com.cd.entity.Customer;
import com.cd.entity.OrderItem;
import com.cd.repository.AddressRepository;
import com.cd.repository.CustomerRepository;
import com.cd.repository.OrderItemRepository;
import com.cd.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private OrderItemRepository orderItemRepo;

	private RazorpayClient client;

	@Value("${razorpay.key.id}")
	private String keyId;

	@Value("${razorpay.key.secret}")
	private String keySecret;

	public OrderResponse createOrder(PurchageDTO purchageDto) throws Exception {
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", purchageDto.getOrder().getTotalprice() * 100);
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", purchageDto.getCustomer().getEmail());
		this.client = new RazorpayClient(keyId, keySecret);

		Order razorPayOrder = client.Orders.create(orderRequest);

		// ----------------------------------------

		Customer customer = custRepo.findByEmail(purchageDto.getCustomer().getEmail());

		if (customer == null) {
			customer = new Customer();
			customer.setName(purchageDto.getCustomer().getName());
			customer.setEmail(purchageDto.getCustomer().getEmail());
			customer.setPhno(purchageDto.getCustomer().getPno());
			custRepo.save(customer);
		}
		// ----------------------------------------
		Address address = new Address();
		address.setCustomer(customer);
		address.setStreet(purchageDto.getAddress().getStreet());
		address.setCity(purchageDto.getAddress().getCity());
		address.setState(purchageDto.getAddress().getState());
		address.setZipcode(purchageDto.getAddress().getZipcode());
		addressRepo.save(address);

		// ----------------------------------------

		com.cd.entity.Order newOrder = new com.cd.entity.Order();
		String orderTrackingNum = generateOrderTrackingId();
		newOrder.setOrderTrackingNum(orderTrackingNum);
		newOrder.setAddress(address);
		newOrder.setCustomer(customer);
		newOrder.setTotalQuantity(purchageDto.getOrder().getTotalquantity());
		newOrder.setTotalPrice(purchageDto.getOrder().getTotalprice());
		newOrder.setEmail(customer.getEmail());
		newOrder.setRazorPayOrderId(razorPayOrder.get("id"));
		newOrder.setOrderStatus(razorPayOrder.get("status"));
		orderRepo.save(newOrder);

		// ----------------------------------------

		List<OrderItemDTO> orderItems = purchageDto.getOrderItems();
		for (OrderItemDTO itemDTO : orderItems) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(itemDTO, orderItems);
			//orderItem.setOrder(newOrder); //
			orderItemRepo.save(orderItem);
		}

		// Create and return the OrderResponse
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setRazorpayOrderId(razorPayOrder.get("id"));
		orderResponse.setOrderStatus(razorPayOrder.get("status"));
		orderResponse.setOrderTrackingNumber(orderTrackingNum);

		return orderResponse;

	}

	public boolean verifyPaymentAndUpdateOrderStatus(PaymentCallBackDTO paymentCallbackDTO) {
		com.cd.entity.Order order = orderRepo.findByRazorPayOrderId(paymentCallbackDTO.getRazorpayOrderId());
		System.out.println("RazorPayOrderId :" + order.getRazorPayOrderId());
		boolean isPaymentConfirmed = false;
		if (order != null) {
			try {
				// Verify the payment signature
				boolean isValid = verifySignature(paymentCallbackDTO);

				if (isValid) {
					// Update order status and save the order
					order.setOrderStatus("Confirmed");
					order.setDeliveryDate(deliveryDate());
					order.setRazorPayPaymentId(paymentCallbackDTO.getRazorpayPaymentId());
					orderRepo.save(order); // Save updated order to the database
					isPaymentConfirmed = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isPaymentConfirmed;
	}

	private boolean verifySignature(PaymentCallBackDTO paymentCallbackDTO) throws RazorpayException {
		String generatedSignature = HmacSHA256(
				paymentCallbackDTO.getRazorpayOrderId() + "|" + paymentCallbackDTO.getRazorpayPaymentId(), keySecret);
		System.out.println("Signature :" + generatedSignature);
		return generatedSignature.equals(paymentCallbackDTO.getRazorpaySignature());
	}

	private String HmacSHA256(String data, String key) throws RazorpayException {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			mac.init(secretKeySpec);
			byte[] hash = mac.doFinal(data.getBytes());
			return new String(Hex.encodeHex(hash));
		} catch (Exception e) {
			throw new RazorpayException("Failed to calculate signature.", e);
		}
	}

	private String generateOrderTrackingId() {

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String randomPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return "ORD-" + timestamp + "-" + randomPart;
	}
	
	public List<com.cd.entity.Order> getOrderDetails(String email){
		return orderRepo.findByEmail(email);
	}
	
	private LocalDate deliveryDate() {
		LocalDate currentDate=LocalDate.now();
		LocalDate futureDate=currentDate.plusDays(2);
		return futureDate;
	}

}
