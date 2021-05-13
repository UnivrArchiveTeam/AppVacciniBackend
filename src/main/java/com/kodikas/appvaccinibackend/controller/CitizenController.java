package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.service.CittadinoService;
import com.kodikas.appvaccinibackend.wrapper.CitizenWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cittadini")
@AllArgsConstructor
public class CitizenController {
    private CittadinoService cittadinoService;

    @GetMapping
    public CitizenWrapper getCittadini() {
        return new CitizenWrapper(
                cittadinoService.getCittadini()
        );
    }

    @GetMapping(path = "/{codiceFiscale}")
    public Citizen getCittadino(@PathVariable String codiceFiscale) {
        return cittadinoService.getCittadino(codiceFiscale);
    }

    @PutMapping(path = "/registrato/{codiceFiscale}")
    public Citizen modifyRegistrato(@PathVariable String codiceFiscale) {
        return cittadinoService.modifyRegistrato(codiceFiscale);
    }

}
