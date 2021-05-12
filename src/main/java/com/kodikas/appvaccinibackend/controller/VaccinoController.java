package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccinoService;
import com.kodikas.appvaccinibackend.wrapper.VaccinoWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vaccini")
public class VaccinoController {
    private final VaccinoService vaccinoService;

    public VaccinoController(VaccinoService vaccinoService) {
        this.vaccinoService = vaccinoService;
    }

    @GetMapping
    public VaccinoWrapper getVaccini(){
        return new VaccinoWrapper(vaccinoService.getVaccini());
    }

    @PostMapping
    public Vaccine addVaccino(@RequestBody Vaccine vaccine) {
        return vaccinoService.addVaccino(vaccine);
    }

    @PutMapping(path = "/{idVaccino}/quantità/{quantità}")
    public Vaccine modifyQuantità(
            @PathVariable Long idVaccino,
            @PathVariable Long quantità
    ) {
        return vaccinoService.aggiungiQuantità(idVaccino, quantità);
    }
}
