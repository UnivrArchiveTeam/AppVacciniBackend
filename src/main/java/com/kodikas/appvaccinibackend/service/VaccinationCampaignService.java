package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VaccinationCampaignService {
    private final VaccinationCampaignRepository vaccinationCampaignRepository;

    public List<VaccinationCampaign> getVaccinationCampaigns() {
        return vaccinationCampaignRepository.findAll();
    }

    public VaccinationCampaign addVaccinationCampaign(VaccinationCampaign vaccinationCampaign) {
        if (
                vaccinationCampaignRepository.existsById(vaccinationCampaign.getCampaignID())
        ) {
            throw new IllegalStateException("The given id is already taken");
        }
        else if (
                vaccinationCampaign.getDiseaseName() != null
                        && vaccinationCampaignRepository.existsByDiseaseName(vaccinationCampaign.getDiseaseName())
        ) {
            throw new IllegalStateException("The given diseaseName is already present");
        }
        else if (vaccinationCampaign.getVaccines() != null) {
            vaccinationCampaign.getVaccines().forEach(vaccine -> vaccine.setVaccinationCampaign(vaccinationCampaign));
        }
        return vaccinationCampaignRepository.save(vaccinationCampaign);
    }
}