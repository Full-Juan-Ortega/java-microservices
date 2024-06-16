package com.java_microservices.inventory_service.repositories;

import com.java_microservices.inventory_service.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepositories extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM inventories)", nativeQuery = true)
    boolean existsAny();

    Optional<Inventory> findBySku(String sku);
    List<Inventory> findBySkuIn(List<String> skuList);
}
