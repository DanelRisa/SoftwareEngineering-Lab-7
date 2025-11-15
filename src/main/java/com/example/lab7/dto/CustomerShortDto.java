package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerShortDto {
    private Long id;
    private String name;
    private String email;
}