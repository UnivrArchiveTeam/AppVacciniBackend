package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.service.VaccinationCampaignService;
import com.kodikas.appvaccinibackend.wrapper.VaccinationCampaignWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/vaccinationCampaign")
public class VaccinationCampaignController {
	private final VaccinationCampaignService vaccinationCampaignService;

	@Autowired
	public VaccinationCampaignController(VaccinationCampaignService vaccinationCampaignService) {
		this.vaccinationCampaignService = vaccinationCampaignService;
	}

	@GetMapping
	public VaccinationCampaignWrapper getVaccinationCampaigns() {
		return new VaccinationCampaignWrapper(
				vaccinationCampaignService.getVaccinationCampaigns()
		);
	}

	@PostMapping
	public VaccinationCampaign addVaccinationCampaign(@RequestBody VaccinationCampaign vaccinationCampaign) {
		return vaccinationCampaignService.addVaccinationCampaign(vaccinationCampaign);
	}
}