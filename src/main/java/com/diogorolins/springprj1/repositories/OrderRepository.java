package com.diogorolins.springprj1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	Page<Order> findByClient(Client client, Pageable page);
}
