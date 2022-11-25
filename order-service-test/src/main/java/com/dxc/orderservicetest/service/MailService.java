package com.dxc.orderservicetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private MailSender mailSender;
	
	public void sendMessage(SimpleMailMessage simpleMessage)
	{
		this.mailSender.send(simpleMessage);
	}
	
}
