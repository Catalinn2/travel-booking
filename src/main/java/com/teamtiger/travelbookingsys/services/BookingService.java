package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.BookingDTO;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);

    List<BookingDTO> getAllBooking();

    Optional<BookingDTO> getBookingById(Long id);

    BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO);

    void deleteBooking(Long id);
}