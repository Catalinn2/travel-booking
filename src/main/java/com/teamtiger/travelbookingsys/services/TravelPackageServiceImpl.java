package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;
import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import com.teamtiger.travelbookingsys.repositories.TravelPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public TravelPackageDTO createPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = new TravelPackage();
        travelPackageEntity.setPackageName(travelPackageDTO.getPackageName());
        travelPackageEntity.setDescription(travelPackageDTO.getDescription());
        travelPackageEntity.setDestination(travelPackageDTO.getDestination());
        travelPackageEntity.setDuration(travelPackageDTO.getDuration());
        travelPackageEntity.setPricePerPerson(travelPackageDTO.getPricePerPerson());

        TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackageEntity);
        log.info("Travel package {} was saved", savedTravelPackage.getPackageName());

        TravelPackageDTO travelPackageResponseDTO = new TravelPackageDTO();
        travelPackageResponseDTO.setId(savedTravelPackage.getId());
        travelPackageResponseDTO.setPackageName(savedTravelPackage.getPackageName());
        travelPackageResponseDTO.setDescription(savedTravelPackage.getDescription());
        travelPackageResponseDTO.setDestination(savedTravelPackage.getDestination());
        travelPackageResponseDTO.setDuration(savedTravelPackage.getDuration());
        travelPackageResponseDTO.setPricePerPerson(savedTravelPackage.getPricePerPerson());

        return travelPackageResponseDTO;
    }
}
