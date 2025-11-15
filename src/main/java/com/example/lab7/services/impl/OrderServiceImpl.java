package com.example.lab7.services.impl;

import com.example.lab7.dto.OrderCreateDto;
import com.example.lab7.dto.OrderDto;
import com.example.lab7.mapper.OrderMapper;
import com.example.lab7.models.Customer;
import com.example.lab7.models.Dish;
import com.example.lab7.models.Order;
import com.example.lab7.repositories.CustomerRepository;
import com.example.lab7.repositories.DishRepository;
import com.example.lab7.repositories.OrderRepository;
import com.example.lab7.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDto getOrder(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<OrderDto> getOrdersByCustomer(Long customerId) {
        return orderMapper.toDtoList(orderRepository.findByCustomerId(customerId));
    }
//
    @Override
    public List<OrderDto> getOrdersByStatus(String status) {
        return orderMapper.toDtoList(orderRepository.findByStatus(status));
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderCreateDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElse(null);
        if (customer == null) {
            return null;
        }

        List<Dish> dishes = dishRepository.findAllById(dto.getDishIds());
        if (dishes.size() != dto.getDishIds().size()) {
            return null;
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setDishes(dishes);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        order.setTotalAmount(calculateTotalAmount(dishes));

        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }

        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }

//    @Override
//    @Transactional
//    public OrderDto addDishToOrder(Long orderId, Long dishId) {
//        Order order = orderRepository.findById(orderId).orElse(null);
//        Dish dish = dishRepository.findById(dishId).orElse(null);
//
//        if (order == null || dish == null) {
//            return null;
//        }
//
//        if (order.getDishes().stream().anyMatch(d -> d.getId().equals(dishId))) {
//            return orderMapper.toDto(order); // Блюдо уже есть в заказе
//        }
//
//        order.getDishes().add(dish);
//        order.setTotalAmount(calculateTotalAmount(order.getDishes()));
//
//        Order updated = orderRepository.save(order);
//        return orderMapper.toDto(updated);
//    }
//
//    @Override
//    @Transactional
//    public OrderDto removeDishFromOrder(Long orderId, Long dishId) {
//        Order order = orderRepository.findById(orderId).orElse(null);
//        if (order == null) {
//            return null;
//        }
//
//        // Удаляем блюдо из заказа
//        boolean removed = order.getDishes().removeIf(dish -> dish.getId().equals(dishId));
//        if (!removed) {
//            return orderMapper.toDto(order); // Блюдо не было найдено в заказе
//        }
//
//        order.setTotalAmount(calculateTotalAmount(order.getDishes()));
//        Order updated = orderRepository.save(order);
//        return orderMapper.toDto(updated);
//    }

    @Override
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    @Override
//    public OrderDto updateOrder(Long id, OrderDto dto) {
//        if (!orderRepository.existsById(id)) {
//            return null;
//        }
//
//        Order order = orderMapper.toEntity(dto);
//        order.setId(id);
//        Order updated = orderRepository.save(order);
//        return orderMapper.toDto(updated);
//    }

    private Double calculateTotalAmount(List<Dish> dishes) {
        return dishes.stream()
                .mapToDouble(Dish::getPrice)
                .sum();
    }
}