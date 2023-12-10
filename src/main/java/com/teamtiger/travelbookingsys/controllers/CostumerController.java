package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.CostumerDTO;
import com.teamtiger.travelbookingsys.services.CostumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costumers")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @PostMapping
    public ResponseEntity<CostumerDTO> createCostumer(@RequestBody CostumerDTO costumerDTO) {
        return ResponseEntity.ok(costumerService.createCostumer(costumerDTO));
    }
}
