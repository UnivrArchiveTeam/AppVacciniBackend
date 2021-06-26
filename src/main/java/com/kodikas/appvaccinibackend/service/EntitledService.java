package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntitledService {

    private final EntitledRepository entitledRepository;
    private final VaccineRepository vaccineRepository;

    public List<Entitled> getAllEntitled () {
        return entitledRepository.findAll();
    }

    public Entitled addEntitled (Entitled entitled) {
        if (entitled.getVaccine() != null) {
            Vaccine vaccine = vaccineRepository
                    .findById(entitled.getVaccine().getVaccineID())
                    .orElseThrow(IllegalArgumentException::new);
            entitled.setVaccine(vaccine);
        }
        return entitledRepository.save(entitled);
    }

    public List<Entitled> getEntitledByCategory(String category){
        List<Entitled> entitledList = entitledRepository.findAllByCategory(category);

//        if(entitledList.isEmpty())
//            throw new IllegalStateException("I have not found anyone entitled to this category");


        return entitledList;
    }


    public List<String> getCategories() {
        return entitledRepository.findAll().stream().map(Entitled::getCategory).collect(Collectors.toList());
    }
}
