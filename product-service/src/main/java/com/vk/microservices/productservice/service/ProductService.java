package com.vk.microservices.productservice.service;

import com.vk.microservices.productservice.dto.ProductRequest;
import com.vk.microservices.productservice.dto.ProductResponse;
import com.vk.microservices.productservice.model.Product;
import com.vk.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .skuCode(productRequest.skuCode())
                .build();

        log.info("Product created successfully!");
        productRepository.save(product);

        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getSkuCode());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product->new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice(), product.getSkuCode()))
                .toList();
    }
}
