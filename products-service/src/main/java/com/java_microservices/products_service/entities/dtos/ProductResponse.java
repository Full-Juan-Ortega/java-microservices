package com.java_microservices.products_service.entities.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String sku;
    private String description;
    private String name;
    private double price;
    private boolean status;
}
