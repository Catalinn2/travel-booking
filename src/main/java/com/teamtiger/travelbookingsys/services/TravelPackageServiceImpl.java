package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.exceptions.TravelPackageNotFoundException;
import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;
import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import com.teamtiger.travelbookingsys.repositories.TravelPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;
    private final ModelMapper modelMapper;

    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository, ModelMapper modelMapper) {
        this.travelPackageRepository = travelPackageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = convertDTOToEntity(travelPackageDTO);

        TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackageEntity);
        log.info("Travel package {} was saved", savedTravelPackage.getPackageName());

        return convertEntityToDTO(savedTravelPackage);
    }

    @Override
    public List<TravelPackageDTO> getAllTravelPackages() {
        return travelPackageRepository.findAll().stream()
                .map(travelPackage -> modelMapper.map(travelPackage, TravelPackageDTO.class))
                .toList();
    }

    @Override
    public void deleteTravelPackage(Long id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id).orElseThrow(() -> new TravelPackageNotFoundException("Travel Package not found."));
        travelPackageRepository.deleteById(id);
    }

    @Override
    public TravelPackageDTO updateTravelPackage(Long id, TravelPackageDTO travelPackageDTO) {
        if (!travelPackageRepository.existsById(id)) {
            throw new TravelPackageNotFoundException("Travel Package with id: " + id + " not found! ");
        }
        TravelPackage travelPackageEntity = convertDTOToEntity(travelPackageDTO);
        travelPackageEntity.setId(id);

        TravelPackage updatedTravelPackage = travelPackageRepository.save(travelPackageEntity);
        return convertEntityToDTO(updatedTravelPackage);
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