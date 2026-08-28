package com.tileandgranite.repository;

import com.tileandgranite.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
    java.util.List<Order> findByCustomerId(Long customerId);
    java.util.List<Order> findByStatus(String status);
}
