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
        }
        else if (
                // TODO add test
                campagnavaccinale.getNomeMalattia() != null
                        && campagnavaccinaleRepository.existsByNomeMalattia(campagnavaccinale.getNomeMalattia())
        ) {
            throw new IllegalStateException("The given nomeMalattia is already taken");
        }
        else if (campagnavaccinale.getVaccini() != null) {
            campagnavaccinale.getVaccini().forEach(vaccino -> vaccino.setCampagnaVaccinale(campagnavaccinale));
        }
        return campagnavaccinaleRepository.save(campagnavaccinale);
    }
}