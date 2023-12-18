package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getUserById(Long id);

    CustomerDTO updatedCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}