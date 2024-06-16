package com.java_microservices.orders_service.repositories;

import com.java_microservices.orders_service.entities.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
