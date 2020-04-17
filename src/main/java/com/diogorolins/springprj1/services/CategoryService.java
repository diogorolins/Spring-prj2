package com.diogorolins.springprj1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;
import com.diogorolins.springprj1.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found: " + Category.class.getSimpleName() + " id " + id));
	}
}
