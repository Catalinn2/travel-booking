package com.teamtiger.travelbookingsys.repositories;

import com.teamtiger.travelbookingsys.models.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
