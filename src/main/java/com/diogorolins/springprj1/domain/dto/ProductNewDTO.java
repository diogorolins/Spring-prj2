package com.diogorolins.springprj1.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.Product;

public class ProductNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "Nome obrigatório")
	private String name;
	
	@NotNull(message = "Preço obrigatrio.")
	private Double price;
	private List<Category> categories = new ArrayList<>();
	
	public ProductNewDTO() {
		
	}
	
	public ProductNewDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();	
		this.categories = product.getCategories();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	
	
}
