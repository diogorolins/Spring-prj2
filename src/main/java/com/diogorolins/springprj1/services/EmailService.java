package com.diogorolins.springprj1.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Order;

@Service
public interface EmailService {
	
	public void sendOrderConfirmationMail(Order order);
	public void SendEmail(SimpleMailMessage msg);
}
