package com.vk.microservices.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String email;
    private  String firstName;
    private String lastName;
}
