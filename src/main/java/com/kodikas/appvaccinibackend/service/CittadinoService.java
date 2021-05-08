package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Cittadino;
import com.kodikas.appvaccinibackend.repository.CittadinoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CittadinoService {
    private CittadinoRepository cittadinoRepository;

    public List<Cittadino> getCittadini() {
        return cittadinoRepository.findAll();
    }

    public Cittadino getCittadino(String codiceFiscale) {
        if (!cittadinoRepository.existsById(codiceFiscale))
            throw new IllegalStateException("Inserire un codice fiscale valido");
        return cittadinoRepository.findById(codiceFiscale).get();
    }

    public Cittadino modifyRegistrato(String codiceFiscale) {
        if (!cittadinoRepository.existsById(codiceFiscale))
            throw new IllegalStateException("Inserire un codice fiscale valido");

        Cittadino cittadino = cittadinoRepository.findById(codiceFiscale).get();
        cittadino.setRegistrato(true);
        return cittadinoRepository.save(cittadino);
    }
}
