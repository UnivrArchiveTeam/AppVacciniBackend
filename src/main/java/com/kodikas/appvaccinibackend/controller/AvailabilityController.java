package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "vaccini/availability")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) { this.availabilityService = availabilityService; }

    @GetMapping
    public AvailabilityWrapper getAvailabilityALL(){
        return new AvailabilityWrapper(availabilityService.getAllAvailability());
    }
    @PostMapping
    public Availability registerNewAvailability(@RequestBody Availability availability){
        return availabilityService.addAvailability(availability);}

    @GetMapping("/availability/{idVaccine}")
    public AvailabilityWrapper getAvailability( @PathVariable long idVaccine ) {
        return new AvailabilityWrapper (availabilityService.getAvailabilitybyId_Vaccine(idVaccine));
    }
}
