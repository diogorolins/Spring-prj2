package com.diogorolins.springprj1.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.Order;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationMail(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		SendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom(sender);
		sm.setTo(order.getClient().getEmail());
		sm.setSubject("Confirmação de pedido");
		sm.setSentDate(new Date());
		sm.setText(order.toString());
		return sm;
	}
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		SendEmail(sm);
		
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom(sender);
		sm.setTo(client.getEmail());
		sm.setSubject("Envio de senha");
		sm.setSentDate(new Date());
		sm.setText("Nova senha:" + newPass);
		return sm;
	}

}
