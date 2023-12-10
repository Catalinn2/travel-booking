package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.CostumerDTO;
import com.teamtiger.travelbookingsys.models.entities.Costumer;
import com.teamtiger.travelbookingsys.repositories.CostumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CostumerServiceImpl implements CostumerService {

    private final CostumerRepository costumerRepository;

    @Override
    public CostumerDTO createCostumer(CostumerDTO costumerDTO) {
        //ValidateDTO
        if (costumerDTO.getFirstName().length() < 3) {
            throw new IllegalArgumentException("Invalid first name!");
        }
        //ConvertDTO to Entity
        Costumer costumerEntity = new Costumer();
        costumerEntity.setFirstName(costumerDTO.getFirstName());
        costumerEntity.setLastName(costumerDTO.getLastName());
        costumerEntity.setEmail(costumerDTO.getEmail());
        costumerEntity.setContact(costumerDTO.getContact());

        //save Entity to Database
        Costumer savedCostumer = costumerRepository.save(costumerEntity);
        log.info("Costumer {} was saved", savedCostumer.getFirstName());
        //convert entity to DTO
        CostumerDTO costumerResponseDTO = new CostumerDTO();
        costumerResponseDTO.setId(savedCostumer.getId());
        costumerResponseDTO.setFirstName(savedCostumer.getFirstName());
        costumerResponseDTO.setLastName(savedCostumer.getLastName());
        costumerResponseDTO.setEmail(savedCostumer.getEmail());
        costumerResponseDTO.setContact(savedCostumer.getContact());

        return costumerResponseDTO;
    }
}
