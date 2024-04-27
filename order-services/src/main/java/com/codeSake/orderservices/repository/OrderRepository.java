package com.codeSake.orderservices.repository;

import com.codeSake.orderservices.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
