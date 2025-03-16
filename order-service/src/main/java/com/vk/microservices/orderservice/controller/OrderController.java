package com.vk.microservices.orderservice.controller;

import com.vk.microservices.orderservice.dto.OrderRequest;
import com.vk.microservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully!";
    }
}
