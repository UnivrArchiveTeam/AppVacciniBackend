package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VaccinationCampaignService {
    private final VaccinationCampaignRepository vaccinationCampaignRepository;
    private final VaccineRepository vaccineRepository;

    public List<VaccinationCampaign> getVaccinationCampaigns() {
        return vaccinationCampaignRepository.findAll();
    }

    public VaccinationCampaign addVaccinationCampaign(VaccinationCampaign vaccinationCampaign) {
        if (vaccinationCampaignRepository.existsById(vaccinationCampaign.getCampaignID())) {
            throw new IllegalStateException("The given id is already taken");
        } else if (vaccinationCampaign.getDiseaseName() != null
                && vaccinationCampaignRepository.existsByDiseaseName(vaccinationCampaign.getDiseaseName())
        ) {
            throw new IllegalStateException("The given diseaseName is already present");
        } else if (vaccinationCampaign.getDiseaseName() == null) {
            throw new IllegalStateException("Invalid diseaseName");
        }

        if (! vaccinationCampaign.getVaccines().isEmpty()) {
            VaccinationCampaign vaccinationCampaign1 = vaccinationCampaignRepository.save(vaccinationCampaign);
            vaccinationCampaign.getVaccines().forEach(vaccine -> vaccine.setVaccinationCampaign(vaccinationCampaign1));
        }
        vaccinationCampaignRepository.save(vaccinationCampaign);
        return vaccinationCampaign;
    }
}