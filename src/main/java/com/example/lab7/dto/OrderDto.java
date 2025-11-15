package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount; //эт можно
    private CustomerShortDto customer;
    private List<DishDto> dishes; //полный дто хз но в будущем шорт
}