package com.diogorolins.springprj1.domain.enums;

public enum PaymentType {
	
	BOLETO(1, "Boleto"), 
	CARD(2, "Cartão");
	

	private int code;
	private String desc;

	private PaymentType (int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public static PaymentType valueOf(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (PaymentType value : PaymentType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status code");

	}
}
