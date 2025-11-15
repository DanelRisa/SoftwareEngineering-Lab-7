package com.example.lab7.services;

import com.example.lab7.dto.CustomerDto;
import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomer(Long id);
    CustomerDto addCustomer(CustomerDto dto);
    CustomerDto updateCustomer(Long id, CustomerDto dto);
    boolean deleteCustomer(Long id);
}