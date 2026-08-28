package com.tileandgranite.controller;

import com.tileandgranite.dto.OrderDTO;
import com.tileandgranite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            if (createdOrder != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating order: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
    }
    
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<?> getOrderByNumber(@PathVariable String orderNumber) {
        OrderDTO order = orderService.getOrderByOrderNumber(orderNumber);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
            if (updatedOrder != null) {
                return ResponseEntity.ok(updatedOrder);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating order: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
    }
}
