package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.service.CampagnaVaccinaleService;
import com.kodikas.appvaccinibackend.wrapper.CampagnaVaccinaleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/campagnevaccinali")
public class CampagnaVaccinaleController {
	private CampagnaVaccinaleService campagnavaccinaleService;

	@Autowired
	public CampagnaVaccinaleController(CampagnaVaccinaleService campagnavaccinaleService) {
		this.campagnavaccinaleService = campagnavaccinaleService;
	}

	@GetMapping
	public CampagnaVaccinaleWrapper getCampagneVaccinali() {
		return new CampagnaVaccinaleWrapper(
				campagnavaccinaleService.getCampagneVaccinali()
		);
	}

	@PostMapping
	public VaccinationCampaign addCampagnaVaccinale(@RequestBody VaccinationCampaign vaccinationCampaign) {
		return campagnavaccinaleService.addCampagnaVaccinale(vaccinationCampaign);
	}
}