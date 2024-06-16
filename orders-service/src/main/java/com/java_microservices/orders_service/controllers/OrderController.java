package com.java_microservices.orders_service.controllers;


import com.java_microservices.orders_service.entities.dtos.OrderRequest;
import com.java_microservices.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newOrder(@RequestBody OrderRequest orderRequest) {
        orderService.newOrder(orderRequest);
    }



}
