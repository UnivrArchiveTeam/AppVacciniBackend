package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Cittadino;
import com.kodikas.appvaccinibackend.service.CittadinoService;
import com.kodikas.appvaccinibackend.wrapper.CittadinoWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cittadini")
@AllArgsConstructor
public class CittadinoController {
    private CittadinoService cittadinoService;

    @GetMapping
    public CittadinoWrapper getCittadini() {
        return new CittadinoWrapper(
                cittadinoService.getCittadini()
        );
    }

    @GetMapping(path = "/{codiceFiscale}")
    public Cittadino getCittadino(@PathVariable String codiceFiscale) {
        return cittadinoService.getCittadino(codiceFiscale);
    }

    @PutMapping(path = "/registrato/{codiceFiscale}")
    public Cittadino modifyRegistrato(@PathVariable String codiceFiscale) {
        return cittadinoService.modifyRegistrato(codiceFiscale);
    }

}
