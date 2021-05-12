package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampagnaVaccinaleRepository extends JpaRepository<VaccinationCampaign, Long> {
    boolean existsByNomeMalattia (String nomeMalattia);
    Optional<VaccinationCampaign> findCampagnaVaccinaleByNomeMalattia (String nomeMalattia);
}