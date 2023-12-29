package com.teamtiger.travelbookingsys.repositories;

import com.teamtiger.travelbookingsys.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT COALESCE(SUM(o.peopleCount), 0) FROM Order o WHERE o.bookingId.id = :bookingId AND o.peopleCount IS NOT NULL AND o.peopleCount != 0")
    int getTotalPeopleCountByBookingId(@Param("bookingId")Long bookingId);
}