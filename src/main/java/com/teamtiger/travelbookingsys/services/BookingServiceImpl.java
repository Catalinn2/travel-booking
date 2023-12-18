package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.BookingDTO;
import com.teamtiger.travelbookingsys.models.entities.Booking;
import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import com.teamtiger.travelbookingsys.repositories.BookingRepository;
import com.teamtiger.travelbookingsys.repositories.TravelPackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TravelPackageRepository travelPackageRepository;


    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    @Override
    public List<BookingDTO> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingDTO> getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDTO);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setDate(booking.getDate());
        bookingDTO.setAvailableSpots(bookingDTO.getAvailableSpots());

        TravelPackage travelPackage = booking.getTravelPackage();
        if (travelPackage != null) {
            bookingDTO.setTravelPackageId(booking.getTravelPackage().getId());
        }
        return bookingDTO;
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        Booking bookingEntity = new Booking();
        bookingEntity.setDate(bookingDTO.getDate());
        bookingEntity.setAvailableSpots(bookingDTO.getAvailableSpots());
        Optional<TravelPackage> travelPackage = travelPackageRepository.findById(bookingDTO.getTravelPackageId());
        if (travelPackage.isPresent()) {
            bookingEntity.setTravelPackage(travelPackage.get());
        } else {
            throw new IllegalArgumentException("Travel package with the ID " + bookingDTO.getTravelPackageId() + " not found");
        }
        return bookingEntity;
    }


    @Override
    public BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            existingBooking.setDate(updatedBookingDTO.getDate());
            existingBooking.setAvailableSpots(updatedBookingDTO.getAvailableSpots());
            Optional<TravelPackage> optionalTravelPackage = travelPackageRepository.findById(id);
            if (optionalTravelPackage.isPresent()) {
                existingBooking.setTravelPackage(optionalTravelPackage.get());
            } else {
                throw new IllegalArgumentException("Travel package ID " + id + " not found");
            }
            return convertToDTO(existingBooking);
        } else {
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}