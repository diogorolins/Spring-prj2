package com.diogorolins.springprj1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.springprj1.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM City obj WHERE obj.state.id = :estateId ORDER BY obj.name")
	public List<City> findCity(@Param("estateId") Integer estateId);
}
