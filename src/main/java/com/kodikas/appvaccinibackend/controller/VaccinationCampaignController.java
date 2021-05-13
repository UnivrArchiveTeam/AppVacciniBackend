package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.service.VaccinationCampaignService;
import com.kodikas.appvaccinibackend.wrapper.VaccinationCampaignWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/campagnevaccinali")
public class VaccinationCampaignController {
	private VaccinationCampaignService campagnavaccinaleService;

	@Autowired
	public VaccinationCampaignController(VaccinationCampaignService campagnavaccinaleService) {
		this.campagnavaccinaleService = campagnavaccinaleService;
	}

	@GetMapping
	public VaccinationCampaignWrapper getCampagneVaccinali() {
		return new VaccinationCampaignWrapper(
				campagnavaccinaleService.getVaccinationCampaigns()
		);
	}

	@PostMapping
	public VaccinationCampaign addCampagnaVaccinale(@RequestBody VaccinationCampaign vaccinationCampaign) {
		return campagnavaccinaleService.addVaccinationCampaign(vaccinationCampaign);
	}
}