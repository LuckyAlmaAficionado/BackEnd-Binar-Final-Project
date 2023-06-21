package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Order;
import com.binar.pemesanantiketpesawat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order pemesan) {
        Order newOrder = orderService.saveOrder(pemesan);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder/{pemesanId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer pemesanId, @RequestBody Order pemesan) {
        Optional<Order> existingOrder = orderService.getOrderById(pemesanId);
        if (existingOrder.isPresent()) {
            pemesan.setOrderId(pemesanId);
            Order updatedOrder = orderService.updateOrder(pemesan);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{pemesanId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer pemesanId) {
        Optional<Order> existingOrder = orderService.getOrderById(pemesanId);
        if (existingOrder.isPresent()) {
            orderService.deleteOrder(pemesanId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllOrder")

    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> pemesanList = orderService.getAllOrder();
        return new ResponseEntity<>(pemesanList, HttpStatus.OK);
    }

    @GetMapping("/getOrderById/{pemesanId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer pemesanId) {
        Optional<Order> pemesan = orderService.getOrderById(pemesanId);
        return pemesan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
