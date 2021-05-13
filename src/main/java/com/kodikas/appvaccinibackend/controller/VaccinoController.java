package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccineService;
import com.kodikas.appvaccinibackend.wrapper.VaccineWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vaccini")
public class VaccinoController {
    private final VaccineService vaccineService;

    public VaccinoController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public VaccineWrapper getVaccini(){
        return new VaccineWrapper(vaccineService.getVaccines());
    }

    @PostMapping
    public Vaccine addVaccino(@RequestBody Vaccine vaccine) {
        return vaccineService.addVaccine(vaccine);
    }

    @PutMapping(path = "/{idVaccino}/quantità/{quantità}")
    public Vaccine modifyQuantità(
            @PathVariable Long idVaccino,
            @PathVariable Long quantità
    ) {
        return vaccineService.addQuantity(idVaccino, quantità);
    }
}
