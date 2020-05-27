package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.diogorolins.springprj1.domain.Address;
import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.services.validations.ClientUpdate;

@ClientUpdate
public class ClientUpdateDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo obrigatório")
	@Length(min=5, max=80, message = "Between 5 and 120 caracters.")
	private String name;
	
	private String email;
	
	@NotEmpty(message = "Campo obrigatório")
	private String cpfCnpj;
	
	private Integer clientType;
	
	@NotEmpty(message = "Pelo menos um endereço")
	private List<Address> addresses = new ArrayList<>();
	
	private String password;
	
	@NotEmpty(message="Pelo menos um telefone")
	private List<String> phones = new ArrayList<>();
	
	public ClientUpdateDTO() {
		
	}
	
	public ClientUpdateDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpfCnpj = obj.getCpfCnpj();
		this.clientType = obj.getClientType().getId();
		this.addresses= obj.getAddresses();
		this.phones = obj.getPhones();
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<Address> getAddresses() {
		return addresses;
	}
	
	

}
