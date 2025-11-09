package com.example.lab7.services;

import com.example.lab7.dto.DishDto;

import java.util.List;

public interface DishService
{
    List<DishDto> getAllDishes();
    DishDto addDish(DishDto dto);
    DishDto getDish(Long id);
    DishDto updateDish(Long id, DishDto dto);
    boolean deleteDish(Long id);
}
