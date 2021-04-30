package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Vaccino;
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
    public Vaccino addVaccino(@RequestBody Vaccino vaccino) {
        return vaccinoService.addVaccino(vaccino);
    }

    @PutMapping(path = "/{idVaccino}/quantità/{quantità}")
    public Vaccino modifyQuantità(
            @PathVariable Long idVaccino,
            @PathVariable Long quantità
    ) {
        return vaccinoService.aggiungiQuantità(idVaccino, quantità);
    }
}
