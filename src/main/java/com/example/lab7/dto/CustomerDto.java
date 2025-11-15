package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<OrderShortDto> orders;
}