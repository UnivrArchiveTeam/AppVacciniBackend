package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class VaccineService {
    private final VaccineRepository vaccineRepository;

    public List<Vaccine> getVaccines() {
        return vaccineRepository.findAll();
    }

    public Vaccine addVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    public Vaccine addQuantity(Long idVaccino, Long quantità) {
        if (quantità <= 0)
            throw new IllegalStateException("La quantità inserita non è valida");
        else if (!vaccineRepository.existsById(idVaccino))
            throw new IllegalStateException("Inserire un'id valido");
        var vaccine = vaccineRepository.findById(idVaccino).get();
        vaccine.setQuantity(vaccine.getQuantity() + quantità);
        return vaccineRepository.save(vaccine);
    }
}
