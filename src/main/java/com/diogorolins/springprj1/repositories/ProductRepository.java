package com.diogorolins.springprj1.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Product obj "
			+ "INNER JOIN obj.categories cat "
			+ "WHERE obj.name LIKE %:name% "
			+ "AND cat IN :categories")
	public Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);
}
