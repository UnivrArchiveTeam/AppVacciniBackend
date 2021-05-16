package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {

    private  final AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailability(){
        return  availabilityRepository.findAll();
    }

    public Availability addAvailability(Availability newEntry){
        return availabilityRepository.save(newEntry);
    }

    public List<Availability> getAvailabilitybyId_Vaccine(Long idVaccine){
        if(idVaccine < 0L){
            throw new IllegalStateException("Invalid vaccine id");
        }
        List<Availability>list_availability = availabilityRepository.findAllById_IdVaccine(idVaccine);
        if(list_availability.isEmpty()){
            throw new IllegalStateException("No availability found matching the vaccine id");
        }
        return list_availability;
    }
}
