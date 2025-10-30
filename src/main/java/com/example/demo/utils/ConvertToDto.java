package com.example.demo.utils;

import com.example.demo.dto.CustomerRequestDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.entity.Customer;

public class ConvertToDto {

    public static Customer converToCustomer(CustomerRequestDto customerDto) {
        Customer customer = new Customer();
        customer.setUsername(customerDto.getUserName());
        customer.setEmail(customerDto.getEmail());
        customer.setRole(customerDto.getRole());
        customer.setEnabled(true);
        return customer;
    }

    public static CustomerResponseDto converToCustomerDto(Customer customer) {
        return CustomerResponseDto.builder()
                .userName(customer.getUsername())
                .email(customer.getEmail())
                .role(customer.getRole())
                .enabled(customer.isEnabled())
                .build();
    }

}
