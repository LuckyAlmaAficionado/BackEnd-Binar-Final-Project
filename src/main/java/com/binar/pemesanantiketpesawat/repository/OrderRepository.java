package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
