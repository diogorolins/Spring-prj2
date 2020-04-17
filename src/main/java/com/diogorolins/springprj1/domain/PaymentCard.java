package com.diogorolins.springprj1.domain;

import com.diogorolins.springprj1.domain.enums.PaymentStatus;

public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installment;
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, PaymentStatus paymentStatus, Order order, Integer installment) {
		super(id, paymentStatus, order);
		this.installment = installment;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}
	
	
}