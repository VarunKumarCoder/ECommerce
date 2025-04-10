package com.cd.threads;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import com.cd.entity.Order;
import com.cd.listner.SpringContext;
import com.cd.utils.PaymentRemainderUtility;

public class PaymentRemaindersChildThread implements Callable<List<Order>> {

private List<Order> orders;
	
	private PaymentRemainderUtility getPaymentReminderUtility() {
        return SpringContext.getBean(PaymentRemainderUtility.class);
    }
	
	@Override
	public List<Order> call() throws Exception {
		sendReminder(orders);
		return orders;
	}

	private void sendReminder(List<Order> orders) {
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			for (Order order : orders) {
				getPaymentReminderUtility().sendPaymentRemainder(order);
			}
		}
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
