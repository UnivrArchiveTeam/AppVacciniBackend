package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CitizenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CitizenService {
	private static final String ERROR_MESSAGE = "Insert a Valid FiscalCode";
	private final CitizenRepository citizenRepository;

	public List<Citizen> getCitizens() {
		return citizenRepository.findAll();
	}

	public Citizen getCitizen(String fiscalCode) {
		Optional<Citizen> optionalCitizen;
		if (fiscalCode != null) {
			optionalCitizen = citizenRepository.findById(fiscalCode);
			if (optionalCitizen.isPresent())
				return optionalCitizen.get();
			else
				throw new IllegalStateException(ERROR_MESSAGE);
		} else
			throw new IllegalStateException(ERROR_MESSAGE);
	}

	public Citizen modifyRegistered(String fiscalCode) {
		Optional<Citizen> optionalCitizen;
		if (fiscalCode != null) {
			optionalCitizen = citizenRepository.findById(fiscalCode);
			if (optionalCitizen.isPresent()) {
				var citizen = optionalCitizen.get();
				citizen.setRegistered(true);
				return citizenRepository.save(citizen);
			} else
				throw new IllegalStateException(ERROR_MESSAGE);
		} else
			throw new IllegalStateException(ERROR_MESSAGE);
	}
}
