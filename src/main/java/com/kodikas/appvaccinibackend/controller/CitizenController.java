package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.service.CitizenService;
import com.kodikas.appvaccinibackend.wrapper.CitizenWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/citizens")
@AllArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @GetMapping
    public CitizenWrapper getCitizens() {
        return new CitizenWrapper(
                citizenService.getCitizens()
        );
    }

    @GetMapping(path = "/{fiscalCode}")
    public Citizen getCitizen(@PathVariable String fiscalCode) {
        return citizenService.getCitizen(fiscalCode);
    }

    @PutMapping(path = "/registered/{fiscalCode}")
    public Citizen modifyRegistered(@PathVariable String fiscalCode) {
        return citizenService.modifyRegistered(fiscalCode);
    }

}
