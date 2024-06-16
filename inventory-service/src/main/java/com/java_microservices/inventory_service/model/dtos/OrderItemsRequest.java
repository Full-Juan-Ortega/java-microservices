package com.java_microservices.inventory_service.model.dtos;


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