package com.java_microservices.products_service.repositories;

import com.java_microservices.products_service.entities.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
