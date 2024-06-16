package com.java_microservices.inventory_service.Utils;


import com.java_microservices.inventory_service.model.entities.Inventory;
import com.java_microservices.inventory_service.repositories.InventoryRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepositories inventoryRepositories;

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
    }
}
