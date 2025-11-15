package com.example.lab7.mapper;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.dto.OrderShortDto;
import com.example.lab7.models.Customer;
import com.example.lab7.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "orders", target = "orders")
    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtoList(List<Customer> customers);

    OrderShortDto orderToShortDto(Order order);
    List<OrderShortDto> ordersToShortDtoList(List<Order> orders);
}