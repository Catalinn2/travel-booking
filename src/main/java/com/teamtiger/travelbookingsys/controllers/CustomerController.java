package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.CustomerDTO;
import com.teamtiger.travelbookingsys.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO costumerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(costumerDTO));
    }
}
