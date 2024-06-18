package com.java_microservices.orders_service.services;

import com.java_microservices.orders_service.entities.model.Order;
import com.java_microservices.orders_service.entities.model.OrderItems;
import com.java_microservices.orders_service.entities.dtos.OrderItemsRequest;
import com.java_microservices.orders_service.entities.dtos.OrderRequest;
import com.java_microservices.orders_service.entities.dtos.BaseResponse;
import com.java_microservices.orders_service.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    //newOrder should check be able in inventory

    //first check be able products in stock
    public BaseResponse newOrder(OrderRequest orderRequest) {

        BaseResponse result = webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();


        if (result != null && !result.hasError()) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemsRequest ->
                            mapOrderItemRequestToOrderItems(orderItemsRequest, order)).toList());
            orderRepository.save(order);
            log.info("Order {} is created", order.getOrderNumber());
            return result;
        }else {
            log.info("Order is not created {}", Arrays.toString(result.errorMessages()));
            return result;
        }
    }

    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }



    private OrderItems mapOrderItemRequestToOrderItems(OrderItemsRequest orderItemsRequest, Order order) {
        return OrderItems.builder()
                .sku(orderItemsRequest.getSku())
                .price(orderItemsRequest.getPrice())
                .quantity(orderItemsRequest.getQuantity())
                .order(order)
                .build();
    }

}
