package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
