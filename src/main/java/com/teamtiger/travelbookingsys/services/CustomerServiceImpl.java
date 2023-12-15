package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.CustomerDTO;
import com.teamtiger.travelbookingsys.models.entities.Customer;
import com.teamtiger.travelbookingsys.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getFirstName().length() < 3) {
            throw new IllegalArgumentException("Invalid first name!");
        }
        Customer customerEntity = createCustomerEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customerEntity);
        log.info("Customer {} was saved", savedCustomer.getFirstName());
        return createCustomerResponseDTO(savedCustomer);
    }

    private static CustomerDTO createCustomerResponseDTO(Customer savedCustomer) {
        CustomerDTO customerResponseDTO = new CustomerDTO();
        customerResponseDTO.setId(savedCustomer.getId());
        customerResponseDTO.setFirstName(savedCustomer.getFirstName());
        customerResponseDTO.setLastName(savedCustomer.getLastName());
        customerResponseDTO.setEmail(savedCustomer.getEmail());
        customerResponseDTO.setPhoneNumber(savedCustomer.getPhoneNumber());

        return customerResponseDTO;
    }

    private static Customer createCustomerEntity(CustomerDTO customerDTO) {
        Customer customerEntity = new Customer();
        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        return customerEntity;
    }
}
