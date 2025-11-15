package com.example.lab7.mapper;

import com.example.lab7.dto.DishDto;
import com.example.lab7.models.Dish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishDto toDto(Dish dish);

    Dish toEntity(DishDto dishDto);

    List<DishDto> toDtoList(List<Dish> dishes);

}