package com.example.lab7.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DishDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private String imageUrl; //уберу потом нврн
}