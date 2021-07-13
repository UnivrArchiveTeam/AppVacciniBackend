package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceUnitTest {

	@Mock
	private AvailabilityRepository availabilityRepository;
	@Mock
	private VaccineRepository vaccineRepository;
	private AvailabilityService underTest;
	private Availability availability;

	@BeforeEach
	void setUp() {
		underTest = new AvailabilityService(availabilityRepository, vaccineRepository);
		availability = new Availability(
				"Golosine", 1L,
				LocalDate.of(2021, 5, 6), LocalDate.of(2021, 5, 21),
				LocalTime.of(9, 0), LocalTime.of(12, 0)
		);
	}

	@Test
	void getAllAvailability__availableAvailabilities_pass() {
		// given
		List<Availability> availabilityList = List.of(availability);
		BDDMockito.given(availabilityRepository.findAll()).willReturn(availabilityList);

		// when
		underTest.getAllAvailability();

		// then
		BDDMockito.then(availabilityRepository).should(times(1)).findAll();
	}

	@Test
	void addAvailability_noVaccine_callRepository() {
		// when
		underTest.addAvailability(availability);

		// then
		BDDMockito.then(availabilityRepository).should(times(1)).save(availability);
	}

	@Test
	void addAvailability_withVaccine_pass() {
		// given
		ArgumentCaptor<Availability> availabilityArgumentCaptor = ArgumentCaptor.forClass(Availability.class);
		long vaccineID = 8L;
		Vaccine passedVaccine = new Vaccine(vaccineID);

		Vaccine expectedVaccine = new Vaccine(
				vaccineID,
				"jansen",
				100L
		);

		availability = new Availability(
				"Golosine", 0L,
				LocalDate.of(2021, 5, 6), LocalDate.of(2021, 5, 21),
				LocalTime.of(9, 0), LocalTime.of(12, 0),
				passedVaccine
		);

		BDDMockito.given(vaccineRepository.findById(vaccineID)).willReturn(
				Optional.of(expectedVaccine)
		);

		// when
		underTest.addAvailability(availability);

		// then
		BDDMockito.then(availabilityRepository)
				.should(times(1))
				.save(availabilityArgumentCaptor.capture());

		BDDAssertions.then(
				availabilityArgumentCaptor
						.getValue()
						.getAvailabilityId()
						.getIdVaccine()
		).isEqualTo(vaccineID);

		BDDAssertions.then(
				availabilityArgumentCaptor
						.getValue()
						.getVaccine()
		).isEqualTo(expectedVaccine);
	}

	@Test
	void getAvailabilityByIdVaccine_availableAvailabilities_pass() {
		// given
		List<Availability> list_availability = List.of(availability);
		long idVaccine = 1L;
		VaccineIdWrapper vaccineWrapper = new VaccineIdWrapper(List.of(idVaccine));
		BDDMockito.given(availabilityRepository.findAllByAvailabilityId_IdVaccine(idVaccine)).willReturn(list_availability);

		// when
		List<Availability> result = underTest.getAvailabilityByIdVaccine(vaccineWrapper);
		boolean check = false;

		// then
		for (Availability find : result) {
			if (find.getAvailabilityId().getIdVaccine() == idVaccine) {
				check = true;
				break;
			}
		}
		BDDAssertions.then(check).isTrue();
	}

	@Test
	void getAvailabilityByIdVaccine_invalidId_throwException() {
		// given
		long id = -123L;
		VaccineIdWrapper vaccineIdWrapper = new VaccineIdWrapper(List.of(id));

		// when
		Throwable throwable = catchThrowable(() -> underTest.getAvailabilityByIdVaccine(vaccineIdWrapper));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Invalid vaccineId");
		BDDMockito.then(availabilityRepository).should(never()).findAllByAvailabilityId_IdVaccine(any());
	}

	@Test
	void getAvailabilityByIdVaccine_noAvailability_throwException() {
		// given
		long id = 232L;
		List<Long> ids = List.of(id);
		VaccineIdWrapper vaccineIdWrapper = new VaccineIdWrapper(ids);

		// when
		Throwable throwable = catchThrowable(() -> underTest.getAvailabilityByIdVaccine(vaccineIdWrapper));

		// then
		BDDMockito.then(availabilityRepository)
				.should(times(1))
				.findAllByAvailabilityId_IdVaccine(any());

		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("No availability found matching the vaccine availabilityId");
	}
}