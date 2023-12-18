package com.teamtiger.travelbookingsys.controllers;

import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;
import com.teamtiger.travelbookingsys.services.TravelPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travelPackages")
public class TravelPackageController {
    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping
    public ResponseEntity<TravelPackageDTO> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.createTravelPackage(travelPackageDTO));
    }

    @GetMapping
    public ResponseEntity<List<TravelPackageDTO>> getAllTravelPackages() {
        return ResponseEntity.ok(travelPackageService.getAllTravelPackages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTravelPackage(@PathVariable Long id) {
        travelPackageService.deleteTravelPackage(id);
        return ResponseEntity.ok("Travel package with id: " + id + " has been successfully deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelPackageDTO> updateTravelPackage(@PathVariable Long id, @RequestBody TravelPackageDTO travelPackageDTO) {
        return ResponseEntity.ok(travelPackageService.updateTravelPackage(id, travelPackageDTO));
    }
}