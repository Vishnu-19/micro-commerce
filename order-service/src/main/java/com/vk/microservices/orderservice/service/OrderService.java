package com.vk.microservices.orderservice.service;

import com.vk.microservices.orderservice.client.InventoryClient;
import com.vk.microservices.orderservice.dto.OrderRequest;
import com.vk.microservices.orderservice.event.OrderPlacedEvent;
import com.vk.microservices.orderservice.model.Order;
import com.vk.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if(isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),orderRequest.userDetails().email(),orderRequest.userDetails().firstName(),orderRequest.userDetails().lastName());
            log.info("Start - sending event to kafka");
            kafkaTemplate.send("order-placed",orderPlacedEvent);
            log.info("End - sent event to kafka");


        }else{
            throw  new RuntimeException("Product with Sku Code " + orderRequest.skuCode()  +" is not in stock");
        }
    }
}
