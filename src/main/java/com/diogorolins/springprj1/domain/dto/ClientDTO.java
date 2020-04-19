package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.diogorolins.springprj1.domain.Client;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Mandatory field")
	@Length(min=5, max=80, message = "Between 5 and 120 caracters.")
	private String name;
	
	@NotEmpty
	@Email(message = "Invalid Email")
	private String email;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Client obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email =obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
