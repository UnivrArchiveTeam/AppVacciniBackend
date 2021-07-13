package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface VaccinationCampaignRepository extends JpaRepository<VaccinationCampaign, Long> {
    boolean existsByDiseaseName (String diseaseName);
    Optional<VaccinationCampaign> findVaccinationCampaignByDiseaseName(String diseaseName);
}