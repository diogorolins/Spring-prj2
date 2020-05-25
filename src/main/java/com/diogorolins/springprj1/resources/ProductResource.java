package com.diogorolins.springprj1.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.domain.dto.ProductDTO;
import com.diogorolins.springprj1.domain.dto.ProductNewDTO;
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
	public ResponseEntity<ProductNewDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new ProductNewDTO(service.findById(id)));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductNewDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		categories = categories.isEmpty() ? URL.getEmptyCategory(catService.findAll()) : categories;
		Page<Product> list = service.search(URL.encodeParam(name), URL.decodeIntList(categories), 
				page, linesPerPage, orderBy, direction);
		Page<ProductNewDTO> listDto = list.map(obj -> new ProductNewDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductNewDTO objDto) {
		Product product = service.fromDto(objDto);
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDTO(product));		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductNewDTO objDto, @PathVariable Integer id) {
		Product product = service.fromDto(objDto);
		product.setId(id);
		product = service.update(product);
		return ResponseEntity.ok().body(new ProductDTO(product));		
	}	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
