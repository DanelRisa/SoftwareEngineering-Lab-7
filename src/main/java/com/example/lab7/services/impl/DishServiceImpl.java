package com.example.lab7.services.impl;

import com.example.lab7.dto.DishDto;
import com.example.lab7.mapper.DishMapper;
import com.example.lab7.models.Dish;
import com.example.lab7.repositories.DishRepository;
import com.example.lab7.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    public List<DishDto> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        return dishMapper.toDtoList(dishes);
    }

    @Override
    public DishDto getDish(Long id) {
        return dishRepository.findById(id)
                .map(dishMapper::toDto)
                .orElse(null);
    }

    @Override
    public DishDto addDish(DishDto dto) {
        Dish dish = dishMapper.toEntity(dto);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toDto(saved);
    }

    @Override
    public DishDto updateDish(Long id, DishDto dto) {
        if (!dishRepository.existsById(id)) {
            return null;
        }
        Dish dish = dishMapper.toEntity(dto);
        dish.setId(id);
        Dish updated = dishRepository.save(dish);
        return dishMapper.toDto(updated);
    }

    @Override
    public boolean deleteDish(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Dish> getDishesByIds(List<Long> ids) {
        return dishRepository.findByIdIn(ids);
    }

    public boolean existsById(Long id) {
        return dishRepository.existsById(id);
    }
}