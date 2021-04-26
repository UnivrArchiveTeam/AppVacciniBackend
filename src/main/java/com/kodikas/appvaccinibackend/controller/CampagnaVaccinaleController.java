package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.service.CampagnaVaccinaleService;
import com.kodikas.appvaccinibackend.wrapper.CampagnaVaccinaleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/campagnevaccinali")
public class CampagnaVaccinaleController {
	private final CampagnaVaccinaleService campagnavaccinaleService;

	@Autowired
	public CampagnaVaccinaleController(CampagnaVaccinaleService campagnavaccinaleService) {
		this.campagnavaccinaleService = campagnavaccinaleService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public CampagnaVaccinaleWrapper getCampagneVaccinali() {
		return new CampagnaVaccinaleWrapper(
				campagnavaccinaleService.getCampagneVaccinali()
		);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addCampagnaVaccinale(@RequestBody CampagnaVaccinale campagnavaccinale) {
		campagnavaccinaleService.addCampagnaVaccinale(campagnavaccinale);
	}
}