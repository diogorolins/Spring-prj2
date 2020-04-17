package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
