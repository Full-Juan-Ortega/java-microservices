package com.java_microservices.inventory_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private int quantity;

}
