package com.diogorolins.springprj1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diogorolins.springprj1.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
