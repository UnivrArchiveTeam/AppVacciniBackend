package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccinoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class VaccinoService {
    private final VaccinoRepository vaccinoRepository;

    public List<Vaccine> getVaccini() {
        return vaccinoRepository.findAll();
    }

    public Vaccine addVaccino(Vaccine vaccine) {
        return vaccinoRepository.save(vaccine);
    }

    public Vaccine aggiungiQuantità(Long idVaccino, Long quantità) {
        if (quantità <= 0)
            throw new IllegalStateException("La quantità inserita non è valida");
        else if (!vaccinoRepository.existsById(idVaccino))
            throw new IllegalStateException("Inserire un'id valido");
        var vaccino = vaccinoRepository.findById(idVaccino).get();
        vaccino.setQuantity(vaccino.getQuantity() + quantità);
        return vaccinoRepository.save(vaccino);
    }
}
