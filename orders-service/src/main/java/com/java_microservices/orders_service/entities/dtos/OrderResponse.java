package com.java_microservices.orders_service.entities.dtos;

import java.util.List;

public record OrderResponse(
        Long id,
        String orderNumber,
        List<OrderItemResponse> orderItems

) {
}
