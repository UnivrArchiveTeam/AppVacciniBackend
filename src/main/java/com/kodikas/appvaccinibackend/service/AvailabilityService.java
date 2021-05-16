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

    public Availability addAvailability(Availability availability){
        if (availability.getVaccine() != null)
            availability.getVaccine().getAvailabilities().add(availability);
        return availabilityRepository.save(availability);
    }

    public List<Availability> getAvailabilityByIdVaccine(Long idVaccine){
        if(idVaccine < 0L){
            throw new IllegalStateException("Invalid vaccine availabilityId");
        }

        List<Availability> availabilityList = availabilityRepository.findAllByAvailabilityId_IdVaccine(idVaccine);
        if(availabilityList.isEmpty()){
            throw new IllegalStateException("No availability found matching the vaccine availabilityId");
        }
        return availabilityList;
    }
}
