package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CitizenRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceUnitTest {
	private static final String ERROR_MESSAGE = "Insert a Valid FiscalCode";
	@Mock
	private CitizenRepository citizenRepository;
	private CitizenService underTest;
	private Citizen citizen;
	private String fiscalCode;

	@BeforeEach
	void setUp() {
		fiscalCode = "MZZMMT61M22D854K";
		underTest = new CitizenService(citizenRepository);
		citizen = new Citizen(
				fiscalCode,
				237971319838010581L,
				"Mouhameth",
				"Mazza",
				"Gaiarine",
				LocalDate.parse("1961-08-22"),
				"paziente iperteso"
		);
	}

	@Test
	void getCitizens_availableCitizens_returnListOfCitizens() {
		// given
		List<Citizen> citizens = List.of(citizen);
		BDDMockito.given(citizenRepository.findAll()).willReturn(citizens);

		// when
		List<Citizen> result = underTest.getCitizens();

		// then
		BDDMockito.then(citizenRepository).should(times(1)).findAll();
		BDDAssertions.then(result).isEqualTo(citizens);

	}

	@Test
	void getCitizen_nullFiscalCode_throwException() {
		// given
		String fc = null;

		// when
		Throwable throwable = catchThrowable(() -> underTest.getCitizen(fc));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage(ERROR_MESSAGE);

		BDDMockito.then(citizenRepository)
				.should(never())
				.findById(any());
	}

	@Test
	void getCitizen_emptyOptional_throwException() {
		// given
		Optional<Citizen> optionalCitizen = Optional.empty();

		BDDMockito.given(citizenRepository.findById(fiscalCode)).willReturn(optionalCitizen);

		// when
		Throwable throwable = catchThrowable(() -> underTest.getCitizen(fiscalCode));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage(ERROR_MESSAGE);
	}

	@Test
	void getCitizen_validFiscalCode_pass() {
		// given
		Optional<Citizen> optionalCitizen = Optional.of(citizen);
		BDDMockito.given(citizenRepository.findById(fiscalCode)).willReturn(optionalCitizen);

		// when
		Citizen result = underTest.getCitizen(fiscalCode);

		// then
		BDDMockito.then(citizenRepository)
				.should(times(1))
				.findById(fiscalCode);
	}

	@Test
	void modifyRegistered_nullFiscalCode_throwException() {
		// when
		Throwable throwable = catchThrowable(() -> underTest.modifyRegistered(null));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage(ERROR_MESSAGE);

		// verify methods were not call
		BDDMockito.then(citizenRepository).should(never()).findById(any());
		BDDMockito.then(citizenRepository).should(never()).save(any());
	}

	@Test
	void modifyRegistered_emptyOptional_throwException() {
		// given
		Optional<Citizen> optionalCitizen = Optional.empty();
		BDDMockito.given(citizenRepository.findById(fiscalCode)).willReturn(optionalCitizen);

		// when
		Throwable throwable = catchThrowable(() -> underTest.modifyRegistered(fiscalCode));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage(ERROR_MESSAGE);

		BDDMockito.then(citizenRepository).should(never()).save(any());
	}

	@Test
	void modifyRegistered_shouldReturnCitizenWithRegisteredFieldTrue() {
		// given
		Optional<Citizen> optionalCitizen = Optional.of(citizen);
		citizen.setRegistered(true);
		BDDMockito.given(citizenRepository.findById(fiscalCode)).willReturn(optionalCitizen);
		BDDMockito.given(citizenRepository.save(citizen)).willReturn(citizen);

		// when
		Citizen result = underTest.modifyRegistered(fiscalCode);

		// then
		BDDMockito.then(citizenRepository).should(times(1)).save(citizen);
		BDDAssertions.then(result.isRegistered()).isTrue();
	}
}