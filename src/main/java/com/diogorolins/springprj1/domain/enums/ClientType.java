package com.diogorolins.springprj1.domain.enums;

public enum ClientType {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int id;
	private String desc;

	private ClientType(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
	public static ClientType toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(ClientType t : ClientType.values()) {
			if(id.equals(t.getId())) return t;
		}
		
		throw new IllegalArgumentException("Invalid Enum");
	}
	
	
	
	
}
