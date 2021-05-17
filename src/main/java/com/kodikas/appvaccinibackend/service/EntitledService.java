package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntitledService {

    private final EntitledRepository entitledRepository ;

    public List<Entitled> getAllEntitled () {
        return entitledRepository.findAll();
    }

    public Entitled addEntitled (Entitled entitled) {
        if (entitled.getVaccine() != null)
            entitled.getVaccine().getEntitleds().add(entitled);
        return entitledRepository.save(entitled);
    }

    public List<Entitled> getEntitledByCategory(String category){
        List<Entitled> entitledList = entitledRepository.findAllByCategory(category);

        if(entitledList.isEmpty())
            throw new IllegalStateException("I have not found anyone entitled to this category");


        return entitledList;
    }


}
