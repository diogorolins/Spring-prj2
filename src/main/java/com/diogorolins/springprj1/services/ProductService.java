package com.diogorolins.springprj1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.domain.dto.ProductNewDTO;
import com.diogorolins.springprj1.exceptions.DatabaseException;
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

	public Product fromDto(ProductNewDTO objDto) {	
		Product product = new Product(objDto.getId(), objDto.getName(), objDto.getPrice());
		for (Category cat : objDto.getCategories()) {
			Optional<Category> category = categoryRepository.findById(cat.getId());
			product.getCategories().add(category.get());
		}
		return product;
	}

	public Product insert(Product obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Product update(Product product) {
		Product newProduct = findById(product.getId());
		product = updateData(newProduct, product); 
		repository.save(product);
		return product;
	}

	private Product updateData(Product newProduct, Product product) {
		newProduct.setName(product.getName());
		newProduct.setPrice(product.getPrice());
		newProduct.getCategories().clear();
		newProduct.getCategories().addAll(product.getCategories());
		return newProduct;
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			Product prodDeleted = findById(id);
			throw new DatabaseException(prodDeleted.getName());
		}
	}
}
