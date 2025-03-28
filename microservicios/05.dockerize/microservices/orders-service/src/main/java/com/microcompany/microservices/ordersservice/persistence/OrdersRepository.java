package com.microcompany.microservices.ordersservice.persistence;

import com.microcompany.microservices.ordersservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
