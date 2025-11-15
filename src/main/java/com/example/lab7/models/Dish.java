// Dish.java (обновленная)
package com.example.lab7.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @ManyToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();
}