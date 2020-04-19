package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;

import com.diogorolins.springprj1.domain.Client;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String cpfCnpj;
	private Integer clientType;
	
	private String street;
	private String number;
	private String compl;
	private String neighborhood;
	private String zipCode;
	
	private String phone1 ;
	private String phone2 ;
	private String phone3 ;
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
	}
	
	

	public ClientNewDTO(Client obj) {
		super();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpfCnpj = obj.getCpfCnpj();
		this.clientType = obj.getClientType().getId();
		this.street = obj.getAddresses().get(0).getStreet();
		this.number = obj.getAddresses().get(0).getNumber();
		this.compl = obj.getAddresses().get(0).getCompl();
		this.neighborhood = obj.getAddresses().get(0).getNeighborhood();
		this.zipCode = obj.getAddresses().get(0).getZipCode();
		this.phone1 = obj.getPhones().iterator().toString();
		this.phone2 = obj.getPhones().iterator().toString();
		this.phone3 = obj.getPhones().iterator().toString();
		this.cityId = obj.getAddresses().get(0).getCity().getId();
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

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
