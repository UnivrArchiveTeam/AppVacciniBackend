package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.IdAvailability;
import com.kodikas.appvaccinibackend.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, IdAvailability> {
    List<Availability> findAllById_IdVaccine(Long vaccine);
}
