package com.diogorolins.springprj1.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.domain.dto.ProductDTO;
import com.diogorolins.springprj1.resources.utils.URL;
import com.diogorolins.springprj1.services.CategoryService;
import com.diogorolins.springprj1.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private CategoryService catService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		categories = categories.isEmpty() ? URL.getEmptyCategory(catService.findAll()) : categories;
		Page<Product> list = service.search(URL.encodeParam(name), URL.decodeIntList(categories), 
				page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
