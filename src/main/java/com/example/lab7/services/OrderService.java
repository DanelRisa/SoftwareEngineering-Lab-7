package com.example.lab7.services;

import com.example.lab7.dto.OrderCreateDto;
import com.example.lab7.dto.OrderDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrder(Long id);
//    OrderDto updateOrder(Long id, OrderDto dto);
    boolean deleteOrder(Long id);

    List<OrderDto> getOrdersByCustomer(Long customerId);
    List<OrderDto> getOrdersByStatus(String status);
    OrderDto createOrder(OrderCreateDto dto);
    OrderDto updateOrderStatus(Long id, String status);
//    OrderDto addDishToOrder(Long orderId, Long dishId);
//    OrderDto removeDishFromOrder(Long orderId, Long dishId);
}