package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderService {
    Order saveOrder(Order pemesan);

    Order updateOrder(Order pemesan);

    void deleteOrder(Integer pemesanId);

    List<Order> getAllOrder();

    Optional<Order> getOrderById(Integer pemesanId);
}
