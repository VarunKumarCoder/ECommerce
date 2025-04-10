package com.cd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cd.entity.Order;
import com.cd.model.EmailDetails;
import com.cd.service.EmailService;
import com.cd.service.OrderService;
import com.cd.service.WatiService;

@Component
public class NotificationUtility {
	@Autowired
	private PdfGeneratorUtility pdfGenerator;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private WatiService watiService;

	public void sendDeliveryNotification(Order order) {
		String invoiceUrl = pdfGenerator.generatePdfReport(order);
		order.setInvoice(invoiceUrl);
		orderService.updateOrder(order);
		EmailDetails emailDetails = EmailDetails.builder().recipient(order.getEmail()).attachment("invoices"+invoiceUrl.substring(invoiceUrl.lastIndexOf("/"), invoiceUrl.length())).
		subject("Your ashokit.in order #"+ order.getOrderTrackingNum()+" has been delivered").msgBody("Hi Your order has been delivered successfully, Please find the invoice for your reference").build();
		emailService.sendMailWithAttachment(emailDetails);
		watiService.sendDeliveryNotification(order.getCustomer().getPno(), order.getCustomer().getName(), order.getOrderTrackingNum());
		orderService.deleteInvoiceFromLocalStorage(order.getOrderTrackingNum());
	}
}
