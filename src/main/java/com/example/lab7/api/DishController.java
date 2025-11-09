package com.example.lab7.api;

import com.example.lab7.dto.DishDto;
import com.example.lab7.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        List<DishDto> dishes = dishService.getAllDishes();
        return dishes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDish(@PathVariable Long id) {
        DishDto dish = dishService.getDish(id);
        return dish == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<DishDto> addDish(@RequestBody DishDto dto) {
        DishDto created = dishService.addDish(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable Long id, @RequestBody DishDto dto) {
        DishDto updated = dishService.updateDish(id, dto);
        return updated == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        boolean deleted = dishService.deleteDish(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
