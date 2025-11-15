package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderShortDto {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
}