package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
