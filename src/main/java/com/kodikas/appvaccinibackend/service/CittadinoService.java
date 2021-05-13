package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CittadinoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CittadinoService {
    private CittadinoRepository cittadinoRepository;

    public List<Citizen> getCittadini() {
        return cittadinoRepository.findAll();
    }

    public Citizen getCittadino(String codiceFiscale) {
        if (!cittadinoRepository.existsById(codiceFiscale))
            throw new IllegalStateException("Inserire un codice fiscale valido");
        return cittadinoRepository.findById(codiceFiscale).get();
    }

    public Citizen modifyRegistrato(String codiceFiscale) {
        if (!cittadinoRepository.existsById(codiceFiscale))
            throw new IllegalStateException("Inserire un codice fiscale valido");

        Citizen citizen = cittadinoRepository.findById(codiceFiscale).get();
        citizen.setRegistered(true);
        return cittadinoRepository.save(citizen);
    }
}
