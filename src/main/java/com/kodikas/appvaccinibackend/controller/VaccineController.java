package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccineService;
import com.kodikas.appvaccinibackend.wrapper.VaccineWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public VaccineWrapper getVaccines(){
        return new VaccineWrapper(vaccineService.getVaccines());
    }

    @PostMapping
    public Vaccine addVaccine(@RequestBody Vaccine vaccine) {
        return vaccineService.addVaccine(vaccine);
    }

    @PutMapping(path = "/{vaccineID}/quantity/{quantity}")
    public Vaccine modifyQuantity(
            @PathVariable Long vaccineID,
            @PathVariable Long quantity
    ) {
        return vaccineService.addQuantity(vaccineID, quantity);
    }

    @GetMapping(path = "/{vaccineID}")
    public Vaccine getVaccine(@PathVariable Long vaccineID) {
        return vaccineService.getVaccine(vaccineID);
    }

    @GetMapping(path = "/campaign/{vaccineID}")
    public VaccinationCampaign getVaccinationCampaign (@PathVariable Long vaccineID) {
        return vaccineService.getVaccinationCampaignByVaccineId(vaccineID);
    }
}