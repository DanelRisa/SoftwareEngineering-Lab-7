package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderCreateDto {
    private Long customerId;
    private List<Long> dishIds;
    private String status;
}