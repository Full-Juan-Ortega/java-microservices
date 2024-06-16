package com.java_microservices.inventory_service.services;

import com.java_microservices.inventory_service.model.dtos.BaseResponse;
import com.java_microservices.inventory_service.model.dtos.OrderItemsRequest;
import com.java_microservices.inventory_service.model.entities.Inventory;
import com.java_microservices.inventory_service.repositories.InventoryRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepositories inventoryRepositories;

    public boolean isInStock(String sku) {
        Optional<Inventory> inventory = inventoryRepositories.findBySku(sku);
        return inventory.isPresent() && inventory.get().getQuantity() > 0;
    }

    public BaseResponse areInStock(List<OrderItemsRequest> orderItems) {
        var errorList = new ArrayList<String>();
        List<String> skuList = orderItems.stream().map(OrderItemsRequest::getSku).toList();
        List<Inventory> inventoryList = inventoryRepositories.findBySkuIn(skuList);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(i -> i.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isEmpty()) {
                errorList.add("Product with sku not found: " + orderItem.getSku());
            } else if (inventory.get().getQuantity() < orderItem.getQuantity())
                errorList.add("Product with sku out of stock: " + orderItem.getSku());
        });
        return errorList.isEmpty() ?
                new BaseResponse(null) : new BaseResponse(errorList.toArray(new String[0]));
    }
}
