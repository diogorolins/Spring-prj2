package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;

import com.diogorolins.springprj1.domain.Client;

public class ClientPasswordDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String password;
	
	
	public ClientPasswordDTO(Client obj) {
		this.id = obj.getId();
		this.password = obj.getPassword();
	}
	
	public ClientPasswordDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
