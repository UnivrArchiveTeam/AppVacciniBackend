package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VaccinationCampaignService {
    private final VaccinationCampaignRepository campagnavaccinaleRepository;

    public List<VaccinationCampaign> getVaccinationCampaigns() {
        return campagnavaccinaleRepository.findAll();
    }

    public VaccinationCampaign addVaccinationCampaign(VaccinationCampaign campagnavaccinale) {
        if (
                campagnavaccinale.getCampaignID() != null
                        && campagnavaccinaleRepository.existsById(campagnavaccinale.getCampaignID())
        ) {
            throw new IllegalStateException("The given id is already taken");
        }
        else if (
                // TODO add test
                campagnavaccinale.getDiseaseName() != null
                        && campagnavaccinaleRepository.existsByDiseaseName(campagnavaccinale.getDiseaseName())
        ) {
            throw new IllegalStateException("The given diseaseName is already taken");
        }
        else if (campagnavaccinale.getVaccines() != null) {
            campagnavaccinale.getVaccines().forEach(vaccino -> vaccino.setVaccinationCampaign(campagnavaccinale));
        }
        return campagnavaccinaleRepository.save(campagnavaccinale);
    }
}