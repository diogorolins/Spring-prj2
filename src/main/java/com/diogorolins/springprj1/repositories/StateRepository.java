package com.diogorolins.springprj1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.springprj1.domain.State;

public interface StateRepository extends JpaRepository<State, Integer>{
	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();
}
