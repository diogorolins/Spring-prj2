package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.springprj1.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Address obj WHERE obj.client.id = :clientId")
	public void deletebyClient(@Param("clientId") Integer clientId);
}
