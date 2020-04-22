package com.diogorolins.springprj1.services;

import org.springframework.mail.SimpleMailMessage;

import com.diogorolins.springprj1.domain.Order;

public interface EmailService {
	
	public void sendOrderConfirmationMail(Order order);
	public void SendEmail(SimpleMailMessage msg);
}
