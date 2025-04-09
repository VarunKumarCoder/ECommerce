package com.cd.service;

import com.cd.model.EmailDetails;

public interface EmailService {

	public String sendMailWithAttachment(EmailDetails details);
}
