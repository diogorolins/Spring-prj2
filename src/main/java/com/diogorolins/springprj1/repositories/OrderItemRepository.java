package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
