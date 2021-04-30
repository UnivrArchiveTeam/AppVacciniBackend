package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import com.kodikas.appvaccinibackend.repository.VaccinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VaccinoService {
    private final VaccinoRepository vaccinoRepository;

    @Autowired
    public VaccinoService(VaccinoRepository vaccinoRepository, CampagnaVaccinaleRepository campagnaVaccinaleRepository) {
        this.vaccinoRepository = vaccinoRepository;
    }

    public List<Vaccino> getVaccini() {
        return vaccinoRepository.findAll();
    }

    public Vaccino addVaccino(Vaccino vaccino) {
        return vaccinoRepository.save(vaccino);
    }

    public Vaccino aggiungiQuantità(Long idVaccino, Long quantità) {
        if (quantità <= 0)
            throw new IllegalStateException("La quantità inserita non è valida");
        else if (!vaccinoRepository.existsById(idVaccino))
            throw new IllegalStateException("Inserire un'id valido");
        Vaccino vaccino = vaccinoRepository.findById(idVaccino).get();
        vaccino.setQuantità(vaccino.getQuantità() + quantità);
        return vaccinoRepository.save(vaccino);
    }
}
