package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;

import java.util.List;

public interface TravelPackageService {
    TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO);

    List<TravelPackageDTO> getAllTravelPackages();

    void deleteTravelPackage(Long id);

    TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO);
}