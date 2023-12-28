package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.CustomerDTO;
import com.teamtiger.travelbookingsys.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO costumerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(costumerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updatedCustomer(@PathVariable Long id, @RequestBody CustomerDTO updatedCustomer) {
        try {
            CustomerDTO updated = customerService.updatedCustomer(id, updatedCustomer);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
