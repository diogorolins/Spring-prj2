package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.diogorolins.springprj1.domain.Client;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Mandatory field")
	@Length(min=5, max=80, message = "Between 5 and 120 caracters.")
	private String name;
	
	@NotEmpty
	@Email(message = "Invalid Email")
	private String email;
	private String cpfCnpj;
	private Integer clientType;
	
	private String street;
	private String number;
	private String compl;
	private String neighborhood;
	private String zipCode;
	
	private List<String> phones = new ArrayList<>();	
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
	}
	
	public ClientNewDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpfCnpj = obj.getCpfCnpj();
		this.clientType = obj.getClientType().getId();
		this.street = obj.getAddresses().get(0).getStreet();
		this.number = obj.getAddresses().get(0).getNumber();
		this.compl = obj.getAddresses().get(0).getCompl();
		this.neighborhood = obj.getAddresses().get(0).getNeighborhood();
		this.zipCode = obj.getAddresses().get(0).getZipCode();
		this.phones = obj.getPhones();
		this.cityId = obj.getAddresses().get(0).getCity().getId();
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCompl() {
		return compl;
	}

	public void setCompl(String compl) {
		this.compl = compl;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}



	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
