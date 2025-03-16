package com.vk.microservices.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.annotation.Generated;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
