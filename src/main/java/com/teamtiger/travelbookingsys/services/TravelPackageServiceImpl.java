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
    public TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = convertDTOToEntity(travelPackageDTO);

        TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackageEntity);
        log.info("Travel package {} was saved", savedTravelPackage.getPackageName());

        return convertEntityToDTO(savedTravelPackage);
    }

    public TravelPackage convertDTOToEntity(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = new TravelPackage();
        travelPackageEntity.setPackageName(travelPackageDTO.getPackageName());
        travelPackageEntity.setDescription(travelPackageDTO.getDescription());
        travelPackageEntity.setDestination(travelPackageDTO.getDestination());
        travelPackageEntity.setDuration(travelPackageDTO.getDuration());
        travelPackageEntity.setPrice(travelPackageDTO.getPrice());
        return travelPackageEntity;
    }

    public TravelPackageDTO convertEntityToDTO(TravelPackage travelPackage) {
        TravelPackageDTO travelPackageResponseDTO = new TravelPackageDTO();
        travelPackageResponseDTO.setId(travelPackage.getId());
        travelPackageResponseDTO.setPackageName(travelPackage.getPackageName());
        travelPackageResponseDTO.setDescription(travelPackage.getDescription());
        travelPackageResponseDTO.setDestination(travelPackage.getDestination());
        travelPackageResponseDTO.setDuration(travelPackage.getDuration());
        travelPackageResponseDTO.setPrice(travelPackage.getPrice());
        return travelPackageResponseDTO;

    }
}