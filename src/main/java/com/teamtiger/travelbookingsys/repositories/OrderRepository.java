package com.teamtiger.travelbookingsys.repositories;

import com.teamtiger.travelbookingsys.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}