package com.example.lab7.api;

import com.example.lab7.dto.OrderCreateDto;
import com.example.lab7.dto.OrderDto;
import com.example.lab7.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return orders.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        OrderDto order = orderService.getOrder(id);
        return order == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomer(customerId);
        return orders.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable String status) {
        List<OrderDto> orders = orderService.getOrdersByStatus(status);
        return orders.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateDto dto) {
        OrderDto created = orderService.createOrder(dto);
        return created == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        OrderDto updated = orderService.updateOrderStatus(id, status);
        return updated == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updated);
    }

//    @PostMapping("/{orderId}/dishes/{dishId}")
//    public ResponseEntity<OrderDto> addDishToOrder(@PathVariable Long orderId, @PathVariable Long dishId) {
//        OrderDto updated = orderService.addDishToOrder(orderId, dishId);
//        return updated == null
//                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                : ResponseEntity.ok(updated);
//    }

//    @DeleteMapping("/{orderId}/dishes/{dishId}")
//    public ResponseEntity<OrderDto> removeDishFromOrder(@PathVariable Long orderId, @PathVariable Long dishId) {
//        OrderDto updated = orderService.removeDishFromOrder(orderId, dishId);
//        return updated == null
//                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                : ResponseEntity.ok(updated);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}