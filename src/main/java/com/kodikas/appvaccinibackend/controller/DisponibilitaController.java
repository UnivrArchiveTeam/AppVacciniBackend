package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "vaccini/availability")
public class DisponibilitaController {

    private AvailabilityService availabilityService;

    @Autowired
    public DisponibilitaController(AvailabilityService availabilityService) { this.availabilityService = availabilityService; }

    @GetMapping
    public AvailabilityWrapper getDisponibilitaALL(){
        return new AvailabilityWrapper(availabilityService.getAllAvailability());
    }
    @PostMapping
    public void registerNewDisponibilita (@RequestBody Availability availability){
        availabilityService.addAvailability(availability);}

    @GetMapping("/availability/{category}/{date}")
    public AvailabilityWrapper getDisponibilita(
            @PathVariable String category,
            @PathVariable LocalDate date
            ){
        return (AvailabilityWrapper) availabilityService.getByDateandCategoryAvailability(category, date);
    }


}
