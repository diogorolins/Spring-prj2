package com.diogorolins.springprj1.domain.enums;

public enum Roles {
	
	ADMIN(1, "ROLE_ADMIN"), 
	CLIENT(2, "ROLE_CLIIENTE");

	private int code;
	private String desc;

	private Roles (int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public static Roles valueOf(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (Roles value : Roles.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid status code");

	}
}
