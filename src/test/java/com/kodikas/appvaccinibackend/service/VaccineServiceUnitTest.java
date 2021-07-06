package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceUnitTest {
	@Mock
	private VaccineRepository vaccineRepository;
	private VaccineService underTest;
	private Vaccine vaccine;

	@BeforeEach
	void setUp() {
		// given
		this.underTest = new VaccineService(vaccineRepository);
		this.vaccine = new Vaccine(
				8L,
				"jansen",
				100L
		);
	}


	@Test
	void getVaccines_availableVaccines_pass() {
		// given
		List<Vaccine> vaccines = List.of(
				vaccine
		);
		given(vaccineRepository.findAll()).willReturn(vaccines);

		// when
		underTest.getVaccines();

		// then
		BDDMockito.then(vaccineRepository).should(times(1)).findAll();
	}

	@Test
	void addVaccine_validVaccine_pass() {
		// when
		underTest.addVaccine(vaccine);

		// then
		BDDMockito.then(vaccineRepository).should(times(1)).save(vaccine);
	}


	@Test
	void addQuantity_validQuantity_pass() {
		// given
		Long id = vaccine.getVaccineID();
		given(vaccineRepository.findById(id)).willReturn(Optional.of(vaccine));
		given(vaccineRepository.save(any(Vaccine.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

		// when
		Vaccine result = underTest.addQuantity(id, 50L);

		// then
		BDDMockito.then(vaccineRepository).should(times(1)).save(any());
		BDDAssertions.then(result.getQuantity()).isEqualTo(150L);
	}

	@Test
	void decreaseQuantity_validData_pass() {
		// given
		given(vaccineRepository.findById(vaccine.getVaccineID())).willReturn(
				Optional.of(vaccine)
		);
		ArgumentCaptor<Vaccine> vaccineArgumentCaptor = ArgumentCaptor.forClass(Vaccine.class);

		// when
		underTest.decreaseQuantity(vaccine.getVaccineID());

		// then
		BDDMockito.then(vaccineRepository).should(times(1)).save(vaccineArgumentCaptor.capture());
		BDDAssertions.then(vaccineArgumentCaptor.getValue().getQuantity()).isEqualTo(99L);
	}

	@Test
	void decreaseQuantity_dosesSmallerThanZero_throwException() {
		// given
		vaccine.setQuantity(0L);
		given(vaccineRepository.findById(vaccine.getVaccineID())).willReturn(
				Optional.of(vaccine)
		);

		// when
		final Throwable throwable = catchThrowable(() -> underTest.decreaseQuantity(vaccine.getVaccineID()));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("The given vaccine has too few doses");

		BDDMockito.then(vaccineRepository).should(never()).save(any());
	}

	@Test
	void decreaseQuantity_idDoesNotExist_throwException() {
		// given
		given(vaccineRepository.findById(anyLong())).willReturn(Optional.empty());

		// when
		final Throwable throwable = catchThrowable(() -> underTest.decreaseQuantity(vaccine.getVaccineID()));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Insert a Valid ID");

		BDDMockito.then(vaccineRepository).should(never()).save(any());
	}

	@Test
	void addQuantity_quantitySmallerThanZero_throwException() {
		// given
		long quantity = -5;
		given(vaccineRepository.findById(vaccine.getVaccineID())).willReturn(
				Optional.of(vaccine)
		);

		// when
		final Throwable throwable = catchThrowable(() -> underTest.addQuantity(vaccine.getVaccineID(), quantity));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Insert a Valid quantity");

		BDDMockito.then(vaccineRepository).should(never()).save(any());
	}

	@Test
	void addQuantity_idDoesNotExist_throwError() {
		// given
		given(vaccineRepository.findById(anyLong())).willReturn(Optional.empty());

		// when
		final Throwable throwable = catchThrowable(() -> underTest.addQuantity(vaccine.getVaccineID(), 50L));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Insert a Valid ID");

		BDDMockito.then(vaccineRepository).should(never()).save(any());
	}

	@Test
	void getVaccine_validId_pass() {
		// given
		BDDMockito.given(vaccineRepository.findById(anyLong())).willReturn(
				Optional.of(vaccine)
		);

		// when
		Vaccine result = underTest.getVaccine(vaccine.getVaccineID());

		// then
		BDDAssertions.then(result).isEqualTo(vaccine);
	}

	@Test
	void getVaccine_invalidId_throwException() {
		// given
		Long id = 3L;
		BDDMockito.given(vaccineRepository.findById(id)).willReturn(
				Optional.empty()
		);

		// when
		Throwable throwable = catchThrowable(() -> underTest.getVaccine(id));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Insert a Valid ID");
	}

	@Test
	void getVaccinationCampaignByVaccineId_validVaccineId_pass() {
		// given
		Long id = 8L;
		VaccinationCampaign vaccinationCampaign = new VaccinationCampaign(
				"campagna2",
				Set.of(
						this.vaccine
				)
		);
		vaccine.setVaccinationCampaign(vaccinationCampaign);
		BDDMockito.given(vaccineRepository.findByVaccineID(id)).willReturn(vaccine);

		// when
		VaccinationCampaign result = underTest.getVaccinationCampaignByVaccineId(id);

		// then
		BDDMockito.then(vaccineRepository).should(times(1)).findByVaccineID(id);
		BDDAssertions.then(result).isEqualTo(vaccinationCampaign);
	}

	@Test
	void getVaccinationCampaignByVaccineId_invalidVaccineId_throwError() {
		// given
		Long id = 3L;

		// when
		Throwable throwable = catchThrowable(() -> underTest.getVaccinationCampaignByVaccineId(id));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Insert a Valid ID");
	}
}