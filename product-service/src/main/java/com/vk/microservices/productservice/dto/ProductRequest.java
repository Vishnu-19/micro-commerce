package com.vk.microservices.productservice.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description, BigDecimal price, String skuCode) {
}
