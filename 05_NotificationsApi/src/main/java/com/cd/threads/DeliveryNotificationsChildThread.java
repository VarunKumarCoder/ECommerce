package com.cd.threads;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import com.cd.entity.Order;
import com.cd.listner.SpringContext;
import com.cd.utils.NotificationUtility;

public class DeliveryNotificationsChildThread implements Callable<List<Order>> {
private List<Order> orders;
	
	private NotificationUtility getNotificationsUtility() {
        return SpringContext.getBean(NotificationUtility.class);
    }
	
	@Override
	public List<Order> call() throws Exception {
		sendDeliveryNotifications(orders);
		return orders;
	}

	private void sendDeliveryNotifications(List<Order> orders) {
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			for (Order order : orders) {
				getNotificationsUtility().sendDeliveryNotification(order);
			}
		}
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
