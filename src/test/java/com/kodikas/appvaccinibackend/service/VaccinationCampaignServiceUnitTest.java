package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccinationCampaignServiceUnitTest {
	@Mock
	private VaccinationCampaignRepository vaccinationCampaignRepository;
	@Mock
	private VaccineRepository vaccineRepository;
	private VaccinationCampaignService underTest;
	private VaccinationCampaign vaccinationCampaign;
	private VaccinationCampaign expectedVaccinationCampaign;

	@BeforeEach
	void setUp() {
		this.underTest = new VaccinationCampaignService(vaccinationCampaignRepository, vaccineRepository);
		this.vaccinationCampaign = new VaccinationCampaign(
				"campagna2",
				Set.of(
						new Vaccine(
								"jansen",
								100L
						)
				)
		);
		this.expectedVaccinationCampaign = new VaccinationCampaign(
				2L,
				"campagna2",
				Set.of(
						new Vaccine(
								1L,
								"jansen",
								100L
						)
				)
		);
	}

	@Test
	void getVaccinationCampaigns_availableCampaigns_returnListOfVaccinationCampaigns() {
		// given
		List<VaccinationCampaign> campaigns = List.of(
				new VaccinationCampaign(
						"campagna1"
				),
				expectedVaccinationCampaign
		);

		BDDMockito.given(vaccinationCampaignRepository.findAll()).willReturn(
				campaigns
		);
		// when
		List<VaccinationCampaign> result = underTest.getVaccinationCampaigns();

		// then
		BDDMockito.then(vaccinationCampaignRepository).should(times(1)).findAll();
		BDDAssertions.then(result.get(0).getDiseaseName()).isNotNull();
	}

	@Test
	void addVaccinationCampaign_validCampaign_pass() {
		// given
		BDDMockito.given(vaccinationCampaignRepository.save(any())).willReturn(expectedVaccinationCampaign);
		BDDMockito.given(vaccinationCampaignRepository.existsById(any())).willReturn(false);

		// when
		underTest.addVaccinationCampaign(vaccinationCampaign);

		// then
		verify(vaccinationCampaignRepository, times(2)).save(vaccinationCampaign);
	}

	@Test
	void addVaccinationCampaign_existingId_throwException() {
		// given
		BDDMockito.given(vaccinationCampaignRepository.existsById(expectedVaccinationCampaign.getCampaignID())).willReturn(true);

		// when
		Throwable throwable = catchThrowable(() -> underTest.addVaccinationCampaign(expectedVaccinationCampaign));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("The given id is already taken");

		BDDMockito.then(vaccinationCampaignRepository)
				.should(never())
				.save(expectedVaccinationCampaign);
	}

	@Test
	void addVaccinationCampaign_existingDisease_shouldThrowException() {
		// when
		BDDMockito.given(
				vaccinationCampaignRepository.existsByDiseaseName(expectedVaccinationCampaign.getDiseaseName())
		).willReturn(true);

		// when
		Throwable throwable = catchThrowable(() -> underTest.addVaccinationCampaign(expectedVaccinationCampaign));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("The given diseaseName is already present");

		BDDMockito.then(vaccinationCampaignRepository).should(never()).save(expectedVaccinationCampaign);
	}

	@Test
	void addVaccinationCampaign_nullDisease_throwException() {
		// given
		vaccinationCampaign.setDiseaseName(null);

		// when
		Throwable throwable = catchThrowable(() -> underTest.addVaccinationCampaign(vaccinationCampaign));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Invalid diseaseName");

		BDDMockito.then(vaccinationCampaignRepository).should(never()).save(vaccinationCampaign);
	}
}