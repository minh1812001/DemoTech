package com.example.demo.order.controller;

import com.example.demo.order.model.Order;
import com.example.demo.order.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderCode) {
        Order order = orderService.getOrderByOrderCode(orderCode);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }
}
