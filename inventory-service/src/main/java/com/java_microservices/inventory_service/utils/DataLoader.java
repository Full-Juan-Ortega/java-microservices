package com.java_microservices.inventory_service.utils;


import com.java_microservices.inventory_service.model.dtos.BaseResponse;
import com.java_microservices.inventory_service.model.dtos.OrderItemsRequest;
import com.java_microservices.inventory_service.model.entities.Inventory;
import com.java_microservices.inventory_service.repositories.InventoryRepositories;
import com.java_microservices.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepositories inventoryRepositories;
    private final InventoryService inventoryService;

    //add inserts in inventories tables.
    @Override
    public void run(String... args) throws Exception {

        if (!inventoryRepositories.existsAny()) {
            log.info("Inventory is empty...dataLoading...");
            inventoryRepositories.save(Inventory.builder().sku("000001").quantity(0).build());
            inventoryRepositories.save(Inventory.builder().sku("000002").quantity(10).build());
            inventoryRepositories.save(Inventory.builder().sku("000003").quantity(20).build());
            inventoryRepositories.save(Inventory.builder().sku("000004").quantity(30).build());
            log.info("Inventory Data Loaded");
        }
        else log.info("Inventory is not empty...nothing to load");

        ArrayList<Boolean> inStock = new ArrayList<>();
        inStock.add(inventoryService.isInStock("000001"));
        inStock.add(inventoryService.isInStock("000002"));
        inStock.add(inventoryService.isInStock("000003"));
        inStock.add(inventoryService.isInStock("000004"));
        log.info("InStock: " + inStock);

        BaseResponse baseResponse =
        inventoryService.areInStock(List.of(
                OrderItemsRequest.builder().sku("000001").quantity(1).price(1.84).build(),
                OrderItemsRequest.builder().sku("000002").quantity(11).price(4.00).build(),
                OrderItemsRequest.builder().sku("000003").quantity(10).price(4.00).build(),
                OrderItemsRequest.builder().sku("000089").quantity(10).price(4.00).build()));
        log.info("BaseResponse: " + Arrays.toString(baseResponse.errorMessages()));
    }
}
