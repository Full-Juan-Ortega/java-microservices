package com.java_microservices.orders_service.entities.dtos;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private List<OrderItemsRequest> orderItems;
}
