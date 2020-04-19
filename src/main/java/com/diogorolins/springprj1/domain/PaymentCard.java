package com.diogorolins.springprj1.domain;

import javax.persistence.Entity;

import com.diogorolins.springprj1.domain.enums.PaymentStatus;
import com.diogorolins.springprj1.domain.enums.PaymentType;

@Entity
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installment;
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, PaymentStatus paymentStatus, PaymentType paymentType, Order order, Integer installment) {
		super(id, paymentStatus, paymentType, order);
		this.installment = installment;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}
	
	
}
