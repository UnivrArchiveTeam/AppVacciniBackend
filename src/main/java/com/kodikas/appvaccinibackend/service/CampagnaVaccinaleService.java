package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampagnaVaccinaleService {
    private final CampagnaVaccinaleRepository campagnavaccinaleRepository;

    @Autowired
    public CampagnaVaccinaleService(CampagnaVaccinaleRepository campagnavaccinaleRepository) {
        this.campagnavaccinaleRepository = campagnavaccinaleRepository;
    }

    public List<CampagnaVaccinale> getCampagneVaccinali() {
        return campagnavaccinaleRepository.findAll();
    }

    public void addCampagnaVaccinale(CampagnaVaccinale campagnavaccinale) {
        if (campagnavaccinaleRepository.existsById(campagnavaccinale.getIdCampagna()))
            throw new IllegalStateException("The given id is already taken");

        campagnavaccinaleRepository.save(campagnavaccinale);
    }
}