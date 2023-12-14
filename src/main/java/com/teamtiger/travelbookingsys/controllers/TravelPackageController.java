package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;
import com.teamtiger.travelbookingsys.services.TravelPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travelPackages")
public class TravelPackageController {
    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping
    public ResponseEntity<TravelPackageDTO> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.createTravelPackage(travelPackageDTO));
    }
}