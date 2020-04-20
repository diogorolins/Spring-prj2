package com.diogorolins.springprj1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;
import com.diogorolins.springprj1.repositories.CategoryRepository;
import com.diogorolins.springprj1.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Integer id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found: " + Product.class.getSimpleName() + " id " + id));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, 
			Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		Page<Product> result = repository.search(name, categories, pageRequest);
		if(result.isEmpty()) {
			throw new ObjectNotFoundException("Produto não encontrado");
		}
		return result;
	}
}
