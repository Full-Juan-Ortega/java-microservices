package com.java_microservices.orders_service.entities.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsRequest {

    private String sku;
    private int quantity;
    private double price;
}