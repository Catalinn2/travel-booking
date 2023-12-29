package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.exceptions.travelPackage.TravelPackageNotFoundException;
import com.teamtiger.travelbookingsys.models.dtos.BookingDTO;
import com.teamtiger.travelbookingsys.models.dtos.DetailedTravelPackageDTO;
import com.teamtiger.travelbookingsys.models.dtos.TravelPackageDTO;
import com.teamtiger.travelbookingsys.models.entities.Booking;
import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import com.teamtiger.travelbookingsys.repositories.BookingRepository;
import com.teamtiger.travelbookingsys.repositories.TravelPackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;
    private final BookingRepository bookingRepository;

    @Override
    public TravelPackageDTO createTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage travelPackageEntity = convertDTOToEntity(travelPackageDTO);

        TravelPackage savedTravelPackage = travelPackageRepository.save(travelPackageEntity);
        log.info("Travel package {} was saved", savedTravelPackage.getPackageName());

        return convertEntityToDTO(savedTravelPackage);
    }

    @Override
    public List<DetailedTravelPackageDTO> getAllTravelPackages() {
        List<TravelPackage> travelPackages = travelPackageRepository.findAll();

        return travelPackages.stream()
                .map(this::convertEntityToDetailedDTO)
                .collect(Collectors.toList());
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


    private DetailedTravelPackageDTO convertEntityToDetailedDTO(TravelPackage travelPackage) {
        DetailedTravelPackageDTO detailedDTO = new DetailedTravelPackageDTO();
        detailedDTO.setId(travelPackage.getId());
        detailedDTO.setPackageName(travelPackage.getPackageName());
        detailedDTO.setDescription(travelPackage.getDescription());
        detailedDTO.setDestination(travelPackage.getDestination());
        detailedDTO.setDuration(travelPackage.getDuration());
        detailedDTO.setPrice(travelPackage.getPrice());

        List<BookingDTO> bookings = bookingRepository.findByTravelPackage(travelPackage)
                .stream()
                .map(this::convertBookingEntityToDTO)
                .collect(Collectors.toList());

        detailedDTO.setBookings(bookings);
        return detailedDTO;
    }

    private BookingDTO convertBookingEntityToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDate(booking.getDate());
        bookingDTO.setAvailableSpots(booking.getAvailableSpots());
        bookingDTO.setTravelPackageId(booking.getTravelPackage().getId());
        return bookingDTO;
    }
}