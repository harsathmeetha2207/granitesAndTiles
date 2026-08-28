package com.tileandgranite.service;

import com.tileandgranite.dto.OrderDTO;
import com.tileandgranite.entity.Order;
import com.tileandgranite.entity.Customer;
import com.tileandgranite.repository.OrderRepository;
import com.tileandgranite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
        
        if (customer.isPresent()) {
            Order order = Order.builder()
                    .orderNumber(orderDTO.getOrderNumber())
                    .customer(customer.get())
                    .totalAmount(orderDTO.getTotalAmount())
                    .status(orderDTO.getStatus())
                    .notes(orderDTO.getNotes())
                    .build();
            
            Order savedOrder = orderRepository.save(order);
            return convertToDTO(savedOrder);
        }
        return null;
    }
    
    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::convertToDTO).orElse(null);
    }
    
    public OrderDTO getOrderByOrderNumber(String orderNumber) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        return order.map(this::convertToDTO).orElse(null);
    }
    
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setOrderNumber(orderDTO.getOrderNumber());
            order.setTotalAmount(orderDTO.getTotalAmount());
            order.setStatus(orderDTO.getStatus());
            order.setNotes(orderDTO.getNotes());
            
            Order updatedOrder = orderRepository.save(order);
            return convertToDTO(updatedOrder);
        }
        return null;
    }
    
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomer().getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .notes(order.getNotes())
                .build();
    }
}
