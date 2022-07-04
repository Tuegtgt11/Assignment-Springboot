package com.example.assignmentspringboot.api;

import com.example.assignmentspringboot.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderApi {

    final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

}
