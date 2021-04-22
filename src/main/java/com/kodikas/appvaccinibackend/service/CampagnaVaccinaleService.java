package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampagnaVaccinaleService {
    private final CampagnaVaccinaleRepository campagnavaccinaleRepository;

    public CampagnaVaccinaleService(CampagnaVaccinaleRepository campagnavaccinaleRepository) {
        this.campagnavaccinaleRepository = campagnavaccinaleRepository;
    }

    public List<CampagnaVaccinale> getCampagneVaccinali() {
        List<CampagnaVaccinale> campagneVaccinali = new ArrayList<>();
        campagnavaccinaleRepository.findAll().forEach(campagneVaccinali::add);
        return campagneVaccinali;
    }

    public CampagnaVaccinale getCampagneVaccinalibyId(Long id) {
        return campagnavaccinaleRepository.findById(id).get();
    }

    public void addCampagnaVaccinale(CampagnaVaccinale campagnavaccinale) {
        campagnavaccinaleRepository.save(campagnavaccinale);
    }

    public void updateCampagnaVaccinale(CampagnaVaccinale campagnavaccinale) {
        campagnavaccinaleRepository.save(campagnavaccinale);
    }

    public void deleteCampagnaVaccinale(Long id) {
        campagnavaccinaleRepository.deleteById(id);
    }
}