package com.java_microservices.products_service.controllers;

import com.java_microservices.products_service.entities.dtos.ProductRequest;
import com.java_microservices.products_service.entities.dtos.ProductResponse;
import com.java_microservices.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductControllers {


    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newProduct(@RequestBody ProductRequest productRequest) {
        this.productService.newProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> listAll() {
        return this.productService.listAll();
    }


}
