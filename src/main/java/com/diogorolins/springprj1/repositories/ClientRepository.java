package com.diogorolins.springprj1.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diogorolins.springprj1.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional(readOnly = true)
	Client findByEmail(String email);
	
}
