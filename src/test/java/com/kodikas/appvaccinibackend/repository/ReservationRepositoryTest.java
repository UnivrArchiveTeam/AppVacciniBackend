package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository underTest;
	private Reservation entry1;
	private Reservation entry2;

	@BeforeEach
	void setUp() {
		entry1 = new Reservation(2L, "GRRDFN68H68L414I", "Fiera",
				LocalDate.of(2021, 5, 20), LocalTime.of(13, 0));
		entry2 = new Reservation(25L, "GRRDFN68H68L414I", "Golosine",
				LocalDate.of(2021, 5, 21), LocalTime.of(13, 0));
		underTest.saveAll(
				List.of(
						entry1, entry2
				)
		);
	}

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void findAllByClinicNameAndReservationId_IdVaccineAndAndDate__availableData__retrieveData() {
		// given
		String clinicName = entry1.getClinicName();
		Long idVaccine = entry1.getReservationId().getIdVaccine();
		LocalDate date = entry1.getDate();

		// when
		List<Reservation> result = underTest.findAllByClinicNameAndReservationId_IdVaccineAndAndDate(clinicName, idVaccine, date);

		// then
		then(result.get(0)).isEqualTo(entry1);
		then(result.size()).isEqualTo(1);
	}

	@Test
	void findAllByClinicNameAndReservationId_IdVaccineAndAndDate__noDataAvailable__retrieveEmptyList() {
		// given
		String clinicName = entry1.getClinicName();
		Long idVaccine = entry1.getReservationId().getIdVaccine();
		LocalDate date = entry1.getDate();
		underTest.deleteAll();

		// when
		List<Reservation> result = underTest.findAllByClinicNameAndReservationId_IdVaccineAndAndDate(clinicName, idVaccine, date);

		// then
		then(result).isEmpty();
	}

	@Test
	void findAllByReservationId_FiscalCodeAndReservationId_IdVaccine__availableData__retrieveData() {
		// given
		String fiscalCode = entry1.getReservationId().getFiscalCode();
		Long idVaccine = entry1.getReservationId().getIdVaccine();

		// when
		List<Reservation> result = underTest.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalCode, idVaccine);

		// then
		then(result).isNotEmpty();
		then(result.size()).isEqualTo(1);
	}

	@Test
	void findAllByReservationId_FiscalCodeAndReservationId_IdVaccine__noDataAvailable__retrieveEmptyList() {
		// given
		String fiscalCode = entry1.getReservationId().getFiscalCode();
		Long idVaccine = entry1.getReservationId().getIdVaccine();
		underTest.deleteAll();

		// when
		List<Reservation> result = underTest.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalCode, idVaccine);

		// then
		then(result).isEmpty();
	}

	@Test
	void findAllByReservationId_FiscalCode__availableData__retrieveData() {
		// given
		String fiscalCode = entry1.getReservationId().getFiscalCode();

		// when
		List<Reservation> result = underTest.findAllByReservationId_FiscalCode(fiscalCode);

		// then
		then(result).isNotEmpty();
		then(result.size()).isEqualTo(2);
	}

	@Test
	void findAllByReservationId_FiscalCode__noDataAvailable__retrieveEmptyList() {
		// given
		String fiscalCode = entry1.getReservationId().getFiscalCode();
		underTest.deleteAll();

		// when
		List<Reservation> result = underTest.findAllByReservationId_FiscalCode(fiscalCode);

		// then
		then(result).isEmpty();
	}
}