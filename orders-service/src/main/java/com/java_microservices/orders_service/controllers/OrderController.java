package com.java_microservices.orders_service.controllers;


import com.java_microservices.orders_service.entities.dtos.BaseResponse;
import com.java_microservices.orders_service.entities.dtos.OrderRequest;
import com.java_microservices.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String newOrder(@RequestBody OrderRequest orderRequest) {
        BaseResponse result = orderService.newOrder(orderRequest);
        if(!result.hasError())
            return "Order created successfully ";
        else
            return "Order not created " + Arrays.toString(result.errorMessages());
    }



}
