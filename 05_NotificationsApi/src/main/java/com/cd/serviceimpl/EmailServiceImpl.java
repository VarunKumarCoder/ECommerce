package com.cd.serviceimpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.cd.model.EmailDetails;
import com.cd.service.EmailService;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {

	private JavaMailSender javaMailSender;

	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		if(StringUtils.isNotEmpty(details.getAttachment())) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			try {
				mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
				mimeMessageHelper.setFrom(sender);
				mimeMessageHelper.setTo(details.getRecipient());
				mimeMessageHelper.setText(details.getMsgBody());
				mimeMessageHelper.setSubject(details.getSubject());
				FileSystemResource file=new FileSystemResource(new File(details.getAttachment()));
				mimeMessageHelper.addAttachment(file.getFilename(), file);
				javaMailSender.send(mimeMessage);
				return "Mail Setn Successfully";
			}catch (MessagingException e) {
				return "Error while sending mail";
			}
		}
		return null;
	}
	
	
}
