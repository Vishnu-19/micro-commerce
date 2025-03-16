package com.vk.microservices.productservice.controller;

import com.vk.microservices.productservice.dto.ProductRequest;
import com.vk.microservices.productservice.dto.ProductResponse;
import com.vk.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> listAllProducts(){
        return productService.getAllProducts();
    }

}
