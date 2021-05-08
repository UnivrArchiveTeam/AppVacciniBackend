package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.repository.VaccinoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class VaccinoService {
    private final VaccinoRepository vaccinoRepository;

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
        var vaccino = vaccinoRepository.findById(idVaccino).get();
        vaccino.setQuantita(vaccino.getQuantita() + quantità);
        return vaccinoRepository.save(vaccino);
    }
}
