package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.BookingDTO;
import com.teamtiger.travelbookingsys.models.entities.Booking;
import com.teamtiger.travelbookingsys.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);

    List<BookingDTO> getAllBooking();

    Optional<BookingDTO> getBookingById(Long id);

    BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO);

    void deleteBooking(Long id);
}