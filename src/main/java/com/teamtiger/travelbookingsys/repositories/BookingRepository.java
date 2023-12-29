package com.teamtiger.travelbookingsys.repositories;

import com.teamtiger.travelbookingsys.models.entities.Booking;
import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTravelPackage(TravelPackage travelPackage);
}
