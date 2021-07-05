package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceUnitTest {

	@Mock
	private ReservationRepository reservationRepository;
	@Mock
	private VaccineService vaccineService;
	private ReservationService underTest;
	private Reservation entry1;
	private Reservation entry2;

	@BeforeEach
	void setUp() {
		underTest = new ReservationService(reservationRepository, vaccineService);
		entry1 = new Reservation(2L, "GRRDFN68H68L414I", "Fiera",
				LocalDate.of(2021, 5, 20), LocalTime.of(13, 0));

		entry2 = new Reservation(25L, "FRRFTH32C49L058J", "Golosine",
				LocalDate.of(2021, 5, 21), LocalTime.of(13, 0));
	}

	@Test
	void getAllReservations_availableReservations_pass() {
		// given
		List<Reservation> reservation_list = List.of(entry1, entry2);
		BDDMockito.given(reservationRepository.findAll()).willReturn(reservation_list);

		// when
		underTest.getAllReservations();

		// then
		BDDMockito.then(reservationRepository).should(times(1)).findAll();
	}

	@Test
	void addReservation_validReservation_pass() {
		// when
		underTest.addReservation(entry1);

		// then
		BDDMockito.then(reservationRepository).should(times(1)).save(entry1);
	}

	@Test
	void getReservation_availableReservations_pass() {
		// given
		List<Reservation> reservation_list = List.of(entry1);
		String fiscalcode = "GRRDFN68H68L414I";
		BDDMockito.given(reservationRepository.findAllByReservationId_FiscalCode(fiscalcode)).willReturn(reservation_list);

		// when
		List<Reservation> result = underTest.getReservation(fiscalcode);

		// then
		boolean check = false;
		for (Reservation find : result) {
			if (!(find.getReservationId().getFiscalCode().equals(fiscalcode))) {
				check = true;
			}
		}

		BDDAssertions.then(check).isFalse();
	}

	@Test
	void getReservation_noReservation_throwError() {
		// given
		String fiscalcode = "GRRDFN68H68L414I";
		List<Reservation> reservation_list = Collections.emptyList();
		BDDMockito.given(reservationRepository
				.findAllByReservationId_FiscalCode(fiscalcode))
				.willReturn(reservation_list);

		// when
		Throwable throwable = catchThrowable(() -> underTest.getReservation(fiscalcode));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("I have not found any reservations for this fiscalCode");
	}

	@Test
	void getReservationByFiscalCode_availableReservation_pass() {
		// given
		List<Reservation> reservation_list = List.of(entry1);
		Long id = 2L;
		VaccineIdWrapper idVaccines = new VaccineIdWrapper(
				List.of(
						id
				)
		);
		String fiscalCode = "GRRDFN68H68L414I";
		BDDMockito.given(
				reservationRepository.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalCode, id)
		).willReturn(reservation_list);

		// when
		List<Reservation> result = underTest.getReservationByFiscalCode(fiscalCode, idVaccines);

		// then
		BDDAssertions.then(result).isEqualTo(reservation_list);
	}

	@Test
	void getReservationByFiscalCode_noReservation_throwException() {
		// given
		Long id = 2L;
		VaccineIdWrapper idVaccines = new VaccineIdWrapper(
				List.of(
						id
				)
		);
		String fiscalCode = "GRRDFN68H68L414I";
		BDDMockito.given(
				reservationRepository.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalCode, id)
		).willReturn(Collections.emptyList());

		// when
		Throwable throwable = catchThrowable(() -> underTest.getReservationByFiscalCode(fiscalCode, idVaccines));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("I have not found any reservations for this fiscalCode");
	}

	@Test
	void getReservationByDate_availableReservation_pass() {
		// given
		String clinicName = entry1.getClinicName();
		Long idVaccine = entry1.getReservationId().getIdVaccine();
		LocalDate date = entry1.getDate();
		List<Reservation> reservationList = List.of(entry1);
		BDDMockito.given(
				reservationRepository.findAllByClinicNameAndReservationId_IdVaccineAndAndDate(clinicName, idVaccine, date)
		).willReturn(
				reservationList
		);

		// when
		List<Reservation> result = underTest.getReservationByDate(clinicName, idVaccine, date);

		// then
		BDDAssertions.then(result).isEqualTo(reservationList);
	}

	@Test
	void getReservationByDate_noReservation_throwException() {
		// given
		String clinicName = entry1.getClinicName();
		Long idVaccine = entry1.getReservationId().getIdVaccine();
		LocalDate date = entry1.getDate();
		BDDMockito.given(
				reservationRepository.findAllByClinicNameAndReservationId_IdVaccineAndAndDate(clinicName, idVaccine, date)
		).willReturn(
				Collections.emptyList()
		);

		// when
		Throwable throwable = catchThrowable(() -> underTest.getReservationByDate(clinicName, idVaccine, date));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("I have not found any reservations in date");
	}
}