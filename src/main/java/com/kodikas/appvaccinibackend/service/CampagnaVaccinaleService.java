package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampagnaVaccinaleService {
    private final CampagnaVaccinaleRepository campagnavaccinaleRepository;

    public List<CampagnaVaccinale> getCampagneVaccinali() {
        return campagnavaccinaleRepository.findAll();
    }

    public CampagnaVaccinale addCampagnaVaccinale(CampagnaVaccinale campagnavaccinale) {
        if (
                campagnavaccinale.getIdCampagna() != null
                        && campagnavaccinaleRepository.existsById(campagnavaccinale.getIdCampagna())
        ) {
            throw new IllegalStateException("The given id is already taken");
        } else if (!campagnavaccinale.getVaccini().isEmpty()) {
            campagnavaccinale.getVaccini().forEach((v) -> v.setCampagnaVaccinale(campagnavaccinale));
        }
        return campagnavaccinaleRepository.save(campagnavaccinale);
    }
}