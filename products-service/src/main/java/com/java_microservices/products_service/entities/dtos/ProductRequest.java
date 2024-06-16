package com.java_microservices.products_service.entities.dtos;


import com.java_microservices.products_service.entities.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String sku;
    private String description;
    private String name;
    private double price;
    private boolean status;

    public Product toProduct() {
        return Product.builder()
                .sku(sku)
                .description(description)
                .name(name)
                .price(price)
                .status(status)
                .build();
    }
}
