package com.java_microservices.products_service.services;

import com.java_microservices.products_service.entities.dtos.ProductRequest;
import com.java_microservices.products_service.entities.dtos.ProductResponse;
import com.java_microservices.products_service.entities.model.Product;
import com.java_microservices.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void newProduct(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toProduct());
        log.info("New product created: {}",product);
    }

    public List<ProductResponse> listAll() {
        var products = productRepository.findAll();
        return products
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
