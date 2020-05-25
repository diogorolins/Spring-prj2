package com.diogorolins.springprj1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.City;
import com.diogorolins.springprj1.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public List<City> findAllByState(Integer stateId) {
		return repository.findCity(stateId);
	}

	
}
