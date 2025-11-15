package com.example.lab7.mapper;

import com.example.lab7.dto.OrderDto;
import com.example.lab7.dto.OrderShortDto;
import com.example.lab7.dto.CustomerShortDto;
import com.example.lab7.dto.DishDto;
import com.example.lab7.models.Order;
import com.example.lab7.models.Customer;
import com.example.lab7.models.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "dishes", target = "dishes")
    OrderDto toDto(Order order);

    OrderShortDto toShortDto(Order order);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);

    CustomerShortDto customerToShortDto(Customer customer);

    DishDto dishToDto(Dish dish);
    List<DishDto> dishesToDtoList(List<Dish> dishes);
}