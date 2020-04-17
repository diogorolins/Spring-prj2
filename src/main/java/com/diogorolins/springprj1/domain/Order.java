package com.diogorolins.springprj1.domain;

import java.io.Serializable;
import java.time.Instant;

public class Order  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Instant instant;
	
	private Payment payment;
	
	private Client client;
	
	private Address address;
	
	public Order() {
		
	}

	public Order(Integer id, Instant instant, Payment payment, Client client, Address address) {
		super();
		this.id = id;
		this.instant = instant;
		this.payment = payment;
		this.client = client;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
