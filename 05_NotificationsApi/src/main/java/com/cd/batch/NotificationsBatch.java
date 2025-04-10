package com.cd.batch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cd.entity.Order;
import com.cd.service.OrderService;
import com.cd.threads.DeliveryNotificationsThread;
import com.cd.threads.PaymentRemaindersThread;

@Component
public class NotificationsBatch {

	@Autowired
	private OrderService orderService;
	
	@Scheduled(cron = "0 0 7 * * *") // 7 AM daily
	public void sendDeliveryNotifications() {
		System.out.println("sendDeliveryNotification "+LocalDateTime.now());
		List<Order> orders = orderService.getOrdersEligibilityForDelivery();
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			Thread notificationsThread = new Thread(new DeliveryNotificationsThread(orders));
			notificationsThread.start();
		}
	}
	
	@Scheduled(cron = "0 0 21 * * *") // 9 PM daily
	public void sendPaymentReminder() {
		System.out.println("sendPaymentReminder "+LocalDateTime.now());
		List<Order> orders = orderService.getUnconfirmedOrders();
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			Thread reminderThread = new Thread(new PaymentRemaindersThread(orders));
			reminderThread.start();
		}
	}
}
