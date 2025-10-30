package com.example.demo.service;

import com.example.demo.dto.CustomerRequestDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.entity.Customer;
import com.example.demo.exceptions.CustomerAlreadyExistException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.utils.ConvertToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponseDto saveUser(CustomerRequestDto customerRequestDto) {

        Customer customerdb = customerRepository.findByEmail(customerRequestDto.getEmail()).orElse(new Customer());

        if (customerdb.getId() == null) {
            Customer customer = ConvertToDto.converToCustomer(customerRequestDto);
            customer.setPassword(passwordEncoder.encode(customerRequestDto.getPassword()));
            customerdb = customerRepository.save(customer);
            return ConvertToDto.converToCustomerDto(customerdb);
        } else {
            throw new CustomerAlreadyExistException("Customer Already exist with: " + customerRequestDto.getEmail());
        }
    }
}
