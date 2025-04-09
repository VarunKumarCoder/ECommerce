package com.cd.service;

import com.cd.model.WatiResponse;

public interface WatiService {

	public WatiResponse sendDeliveryNotification(String phno, String name, String OrderTrackingNumber);
	
	public WatiResponse sendPaymentRemainder(String phno, String name, String orderTrackingNumber);

}
