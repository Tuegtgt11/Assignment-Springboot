package com.example.assignmentspringboot.service;


import com.example.assignmentspringboot.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
