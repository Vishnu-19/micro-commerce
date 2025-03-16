package com.vk.microservices.orderservice.service;

import com.vk.microservices.orderservice.dto.OrderRequest;
import com.vk.microservices.orderservice.model.Order;
import com.vk.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());

        orderRepository.save(order);

        return order;
    }
}
