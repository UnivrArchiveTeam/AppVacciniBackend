package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping
    public AvailabilityWrapper getAvailabilityALL(){
        return new AvailabilityWrapper(
                availabilityService.getAllAvailability()
        );
    }

    @PostMapping
    public Availability registerNewAvailability(@RequestBody Availability availability){
        return availabilityService.addAvailability(availability);
    }

    @GetMapping("/{idVaccine}")
    public AvailabilityWrapper getAvailability( @PathVariable long idVaccine ) {
        return new AvailabilityWrapper(
                availabilityService.getAvailabilitybyId_Vaccine(idVaccine)
        );
    }
}
