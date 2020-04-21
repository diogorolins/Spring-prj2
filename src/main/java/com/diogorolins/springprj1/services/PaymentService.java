package com.diogorolins.springprj1.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.PaymentBoleto;

@Service
public class PaymentService {

	public void fillPaymentBoleto(PaymentBoleto pay, Date instant) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instant);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(calendar.getTime());
		
	}
	

}
