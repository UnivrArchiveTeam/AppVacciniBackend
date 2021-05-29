package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AvailabilityService {

    private  final AvailabilityRepository availabilityRepository;
    private  final VaccineRepository vaccineRepository;

    public List<Availability> getAllAvailability(){
        return  availabilityRepository.findAll();
    }

    public Availability addAvailability(Availability availability){
        if (availability.getVaccine() != null) {
            Vaccine vaccine = vaccineRepository.findById(availability.getVaccine().getVaccineID()).get();
            vaccine.getAvailabilities().add(availability);
            availability.getAvailabilityId().setIdVaccine(
                   vaccine.getVaccineID()
            );
        }
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
