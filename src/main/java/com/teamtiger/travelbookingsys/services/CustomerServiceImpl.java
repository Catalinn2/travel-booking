package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.CustomerDTO;
import com.teamtiger.travelbookingsys.models.entities.Customer;
import com.teamtiger.travelbookingsys.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::createCustomerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getUserById(Long id) {
        return customerRepository.findById(id)
                .map(this::createCustomerResponseDTO);
    }

    @Override
    public CustomerDTO updatedCustomer(Long id, CustomerDTO updatedCustomerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setPhoneNumber(updatedCustomerDTO.getPhoneNumber());
            existingCustomer.setEmail(updatedCustomerDTO.getEmail());
            existingCustomer.setFirstName(updatedCustomerDTO.getFirstName());
            existingCustomer.setLastName(updatedCustomerDTO.getLastName());
            return createCustomerResponseDTO(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + id + " not found");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO createCustomerResponseDTO(Customer savedCustomer) {
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