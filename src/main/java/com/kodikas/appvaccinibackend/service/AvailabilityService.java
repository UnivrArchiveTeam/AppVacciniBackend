package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {

	private final AvailabilityRepository availabilityRepository;
	private final VaccineRepository vaccineRepository;

	public List<Availability> getAllAvailability() {
		return availabilityRepository.findAll();
	}

	public Availability addAvailability(Availability availability) {
		if (availability.getVaccine() != null) {
			// get vaccine object with all parameters
			// the object given from Fronted could not be updated
			Vaccine vaccine = vaccineRepository.findById(availability.getVaccine().getVaccineID()).get();

			// connect vaccine to availability
			availability.getAvailabilityId().setIdVaccine(
					vaccine.getVaccineID()
			);
			availability.setVaccine(vaccine);
		}
		return availabilityRepository.save(availability);
	}

	public List<Availability> getAvailabilityByIdVaccine(VaccineIdWrapper vaccineIdWrapper) {
		List<Availability> availabilityList = null;
		for (long vaccineId : vaccineIdWrapper.getIdVaccines()) {
			if (vaccineId < 0L)
				throw new IllegalStateException("Invalid vaccineId");

			if (availabilityList == null)
				availabilityList = availabilityRepository.findAllByAvailabilityId_IdVaccine(vaccineId);
			else
				availabilityList.addAll(availabilityRepository.findAllByAvailabilityId_IdVaccine(vaccineId));
		}

		if (availabilityList.isEmpty()) {
			throw new IllegalStateException("No availability found matching the vaccine availabilityId");
		}

		return availabilityList;
	}
}
