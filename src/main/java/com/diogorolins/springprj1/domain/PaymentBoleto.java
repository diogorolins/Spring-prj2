package com.diogorolins.springprj1.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.diogorolins.springprj1.domain.enums.PaymentStatus;
import com.diogorolins.springprj1.domain.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date paymentDate;
	
	public PaymentBoleto() {
		
	}

	public PaymentBoleto(Integer id, PaymentStatus paymentStatus, PaymentType paymentType, Order order, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, paymentType, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}
