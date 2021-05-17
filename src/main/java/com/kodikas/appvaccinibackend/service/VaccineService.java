package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public Vaccine addQuantity(Long vaccineID, Long quantity) {
        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(vaccineID);
        Vaccine vaccine;
        if (optionalVaccine.isPresent())
            vaccine = optionalVaccine.get();
        else
            throw new IllegalStateException("Insert a Valid ID");

        if (quantity > vaccine.getQuantity())
            throw new IllegalStateException("Insert a Valid quantity");

        vaccine.setQuantity(vaccine.getQuantity() + quantity);
        return vaccineRepository.save(vaccine);
    }
}
