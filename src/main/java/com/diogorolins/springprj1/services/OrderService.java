package com.diogorolins.springprj1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Order;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;
import com.diogorolins.springprj1.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Integer id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found: " + Order.class.getSimpleName() + " id " + id));
	}
}
