package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.Order;
import com.binar.pemesanantiketpesawat.repository.OrderRepository;
import com.binar.pemesanantiketpesawat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order saveOrder(Order pemesan) {
        return orderRepository.save(pemesan);
    }

    @Override
    public Order updateOrder(Order pemesan) {
        return orderRepository.save(pemesan);
    }

    @Override
    public void deleteOrder(Integer pemesanId) {
        orderRepository.deleteById(pemesanId);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Integer pemesanId) {
        return orderRepository.findById(pemesanId);
    }
}
