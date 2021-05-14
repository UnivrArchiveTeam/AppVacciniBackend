package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.DispId;
import com.kodikas.appvaccinibackend.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepostitory extends JpaRepository<Availability, DispId> {

    List<Availability> findAllByCategoria(String categoria);

    List<Availability> findAllById_IdVaccino(Long vaccino);



}
