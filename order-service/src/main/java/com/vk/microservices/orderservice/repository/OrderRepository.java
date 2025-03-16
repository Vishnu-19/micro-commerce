package com.vk.microservices.orderservice.repository;

import com.vk.microservices.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
