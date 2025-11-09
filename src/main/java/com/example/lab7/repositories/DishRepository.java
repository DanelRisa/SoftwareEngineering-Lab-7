package com.example.lab7.repositories;

import com.example.lab7.models.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
