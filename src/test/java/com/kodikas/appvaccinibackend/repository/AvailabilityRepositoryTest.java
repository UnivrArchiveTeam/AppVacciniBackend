package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AvailabilityRepositoryTest {
	private Availability disponibilita;
	private Availability disponibilita2;
	private Vaccine vaccine;
	private Vaccine vaccine2;
	private String nomeAmbulatorio;
	private Long idVaccino;
	@Autowired private AvailabilityRepository underTest;
	@Autowired private VaccineRepository vaccineRepository;
	private String categoria;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(underTest).isNotNull();
		assertThat(vaccineRepository).isNotNull();
	}

	@BeforeEach
	void setUp() {
		nomeAmbulatorio = "Golosine";
		idVaccino = 1L;
		categoria = "over80";

		disponibilita = new Availability(
				nomeAmbulatorio, idVaccino,
				LocalDate.of(2021, 05, 06), LocalDate.of(2021, 05, 21),
				LocalTime.of(9, 00), LocalTime.of(12, 00));
		disponibilita2 = new Availability(
				"SantaLucia", 2L,
				LocalDate.of(2021, 05, 02), LocalDate.of(2021, 05, 25),
				LocalTime.of(11, 00), LocalTime.of(13, 00));
		vaccine = vaccineRepository.save(
				new Vaccine(
						"jansen",
						100L
				)
		);
		vaccine2 = vaccineRepository.save(
				new Vaccine(
						"Pfizer",
						500L
				)
		);
		idVaccino = vaccine.getVaccineID();
		disponibilita.getAvailabilityId().setIdVaccine(vaccine.getVaccineID());
		disponibilita.setVaccine(vaccine);
		underTest.save(disponibilita);

	}

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
		vaccineRepository.deleteAll();
	}

	@Test
	void findAllById_IdVaccino__availableData__retrieveData() {
		List<Availability> result = underTest.findAllByAvailabilityId_IdVaccine(idVaccino);
		boolean test = true;
		for (Availability find : result) {
			if (!(find.getAvailabilityId().getIdVaccine() == disponibilita.getAvailabilityId().getIdVaccine())) {
				test = false;
			}
		}
		assertThat(test).isTrue();
		assertThat(result.isEmpty()).isFalse();
	}

	@Test
	void findAllById_IdVaccino__noData__retrieveEmptyList() {
		underTest.deleteAll();
		List<Availability> result = underTest.findAllByAvailabilityId_IdVaccine(idVaccino);
		assertThat(result.size()).isZero();
	}


	@Test
	void findAllById_IdVaccino__multipleDataAvailable__retrieveCorrectData() {
		// given
		disponibilita2.getAvailabilityId().setIdVaccine(vaccine2.getVaccineID());
		disponibilita2.setVaccine(vaccine2);
		underTest.save(disponibilita2);

		// when
		List<Availability> result = underTest.findAllByAvailabilityId_IdVaccine(idVaccino);

		// then
		boolean test = true;
		for (Availability find : result) {
			if (!(find.getAvailabilityId().getIdVaccine() == disponibilita.getAvailabilityId().getIdVaccine())) {
				test = false;
			}
		}
		assertThat(test).isTrue();
	}
}