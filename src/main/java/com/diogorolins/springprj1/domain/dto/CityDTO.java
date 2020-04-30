package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;

import com.diogorolins.springprj1.domain.City;

public class CityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String state;
	
	public CityDTO() {
		
	}
	
	public CityDTO(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.state = city.getState().getName(); 
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
