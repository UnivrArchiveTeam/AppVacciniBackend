package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampagnaVaccinaleRepository extends JpaRepository<CampagnaVaccinale, Long> {
}