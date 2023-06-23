package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
