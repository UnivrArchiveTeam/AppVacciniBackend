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

    public List<Entitled> getAllEntitled () { return entitledRepository.findAll();}

    public Entitled addEntitled (Entitled newEntry){ return entitledRepository.save(newEntry);}

    public List<Entitled> getEntitledbyCategory(String category){

        List<Entitled> list_entitled = entitledRepository.findAllByCategory(category);

        if(list_entitled.isEmpty()){
            throw new IllegalStateException("I have not found anyone entitled to this category");
        }

        return list_entitled;
    }

}
