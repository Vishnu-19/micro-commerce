package com.vk.microservices.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.vk.microservices.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
