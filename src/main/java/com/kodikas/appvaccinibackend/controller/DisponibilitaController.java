package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.service.DisponibilitaService;
import com.kodikas.appvaccinibackend.wrapper.DisponibilitaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vaccini/disponibilita")
public class DisponibilitaController {

    private DisponibilitaService disponibilitaService;

    @Autowired
    public DisponibilitaController(DisponibilitaService disponibilitaService) { this.disponibilitaService = disponibilitaService; }

    @GetMapping
    public DisponibilitaWrapper getDisponibilitá(){
        return new DisponibilitaWrapper(disponibilitaService.getDisponibilita());
    }
    @PostMapping
    public void registerNewDisponibilita (@RequestBody Disponibilita disponibilita){disponibilitaService.addNewDisponibilita(disponibilita);}
}
