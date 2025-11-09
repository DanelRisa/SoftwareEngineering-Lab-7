package com.example.lab7.services.impl;


import com.example.lab7.dto.DishDto;
import com.example.lab7.models.Dish;
import com.example.lab7.repositories.DishRepository;
import com.example.lab7.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<DishDto> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        List<DishDto> dtoList = new ArrayList<>();
        dishes.forEach(dish -> dtoList.add(toDto(dish)));
        return dtoList;
    }

    @Override
    public DishDto addDish(DishDto dto) {
        Dish dish = toEntity(dto);
        Dish saved = dishRepository.save(dish);
        return toDto(saved);
    }

    @Override
    public DishDto getDish(Long id) {
        return dishRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public DishDto updateDish(Long id, DishDto dto) {
        if (!dishRepository.existsById(id)) {
            return null;
        }
        Dish dish = toEntity(dto);
        dish.setId(id);
        Dish updated = dishRepository.save(dish);
        return toDto(updated);
    }

    @Override
    public boolean deleteDish(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DishDto toDto(Dish dish) {
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .isAvailable(dish.getIsAvailable())
                .imageUrl(dish.getImageUrl())
                .build();
    }

    private Dish toEntity(DishDto dto) {
        Dish dish = new Dish();
        dish.setId(dto.getId());
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setCategory(dto.getCategory());
        dish.setIsAvailable(dto.getIsAvailable());
        dish.setImageUrl(dto.getImageUrl());
        return dish;
    }
}
