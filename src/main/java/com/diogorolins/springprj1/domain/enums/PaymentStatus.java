package com.diogorolins.springprj1.domain.enums;

public enum PaymentStatus {
	
	WAITING_PAYMENT(1, "Pendente"), 
	PAID(2, "Pago"), 
	CANCELED(3, "Cancelado");

	private int code;
	private String desc;

	private PaymentStatus (int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public static PaymentStatus valueOf(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (PaymentStatus value : PaymentStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status code");

	}
}
