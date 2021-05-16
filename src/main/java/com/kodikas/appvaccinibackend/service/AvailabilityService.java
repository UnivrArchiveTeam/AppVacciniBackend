package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepostitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {

    private  final AvailabilityRepostitory availabilityRepostitory;

    public List<Availability> getAllAvailability(){
        return  availabilityRepostitory.findAll();
    }

    public Availability addAvailability(Availability newEntry){
        return availabilityRepostitory.save(newEntry);
    }

    public List<Availability> getAvailabilitybyId_Vaccine(Long idVaccine){
        if(idVaccine < 0L){
            throw new IllegalStateException("Invalid vaccine id");
        }
        List<Availability>list_availability = availabilityRepostitory.findAllById_IdVaccino(idVaccine);
        if(list_availability.isEmpty()){
            throw new IllegalStateException("No availability found matching the vaccine id");
        }
        return list_availability;
    }
}
