package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

}
