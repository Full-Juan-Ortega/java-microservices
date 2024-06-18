package com.java_microservices.orders_service.entities.dtos;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderItemResponse {

    Long id;
    String sku;
    Double price;
    int quantity;
}
