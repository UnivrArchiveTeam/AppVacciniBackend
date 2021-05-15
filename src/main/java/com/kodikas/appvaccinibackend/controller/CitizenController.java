package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.service.CitizenService;
import com.kodikas.appvaccinibackend.wrapper.CitizenWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cittadini")
@AllArgsConstructor
public class CitizenController {
    private CitizenService cittadinoService;

    @GetMapping
    public CitizenWrapper getCittadini() {
        return new CitizenWrapper(
                cittadinoService.getCitizens()
        );
    }

    @GetMapping(path = "/{codiceFiscale}")
    public Citizen getCittadino(@PathVariable String codiceFiscale) {
        return cittadinoService.getCitizen(codiceFiscale);
    }

    @PutMapping(path = "/registrato/{codiceFiscale}")
    public Citizen modifyRegistrato(@PathVariable String codiceFiscale) {
        return cittadinoService.modifyRegistered(codiceFiscale);
    }

}
