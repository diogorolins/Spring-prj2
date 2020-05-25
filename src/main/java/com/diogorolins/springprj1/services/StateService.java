package com.diogorolins.springprj1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.State;
import com.diogorolins.springprj1.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repository;

	public List<State> findAll() {
		return repository.findAllByOrderByName();
	}
	
	public State findById(Integer id) {
		return repository.findById(id).get();
	}


}
