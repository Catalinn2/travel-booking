package com.teamtiger.travelbookingsys.repositories;

import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage,Long> {
}