package com.java_microservices.orders_service.entities.dtos;

public record BaseResponse(String[] errorMessages) {

    public boolean hasError() {
        return errorMessages != null && errorMessages.length > 0;
    }
}
