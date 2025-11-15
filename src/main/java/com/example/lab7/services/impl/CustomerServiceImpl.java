package com.example.lab7.services.impl;

import com.example.lab7.dto.CustomerDto;
import com.example.lab7.mapper.CustomerMapper;
import com.example.lab7.models.Customer;
import com.example.lab7.repositories.CustomerRepository;
import com.example.lab7.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElse(null);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        Customer customer = customerMapper.toEntity(dto);
        customer.setId(id);
        Customer updated = customerRepository.save(customer);
        return customerMapper.toDto(updated);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}