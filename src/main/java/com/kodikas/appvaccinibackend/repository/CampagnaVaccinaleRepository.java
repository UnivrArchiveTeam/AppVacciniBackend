package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampagnaVaccinaleRepository extends JpaRepository<CampagnaVaccinale, Long> {
    boolean existsByNomeMalattia (String nomeMalattia);
    Optional<CampagnaVaccinale> findCampagnaVaccinaleByNomeMalattia (String nomeMalattia);
}