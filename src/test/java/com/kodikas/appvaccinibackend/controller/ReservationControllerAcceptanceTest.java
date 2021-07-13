package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.wrapper.ReservationWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerAcceptanceTest {
	private final static String URI = "/reservations";

	private Reservation reservation;
	private Reservation reservation2;
	private Vaccine vaccine;
	private String fiscalCode;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private VaccineRepository vaccineRepository;

	@AfterEach
	void tearDown() {
		reservationRepository.deleteAll();
		vaccineRepository.deleteAll();
	}

	@BeforeEach
	void setUp() {
		fiscalCode = "MZZMMT61M22D854K";
		reservation = new Reservation(
				1L,
				fiscalCode,
				"Golosine",
				LocalDate.of(2021, 6, 21),
				LocalTime.of(10, 0)

		);
		reservation2 = new Reservation(
				3L,
				"NNCFNC59C67E709B",
				"San Martino",

				LocalDate.of(2021, 7, 6),
				LocalTime.of(15, 0)

		);
	}

	@Test
	void getAllReservations_shouldRetrieveAllReservationsFromDatabase() throws Exception {
		// given
		List<Reservation> reservations = List.of(
				reservation,
				reservation2
		);
		ReservationWrapper expected = new ReservationWrapper(
				reservations
		);
		reservationRepository.saveAll(reservations);
		// then
		MvcResult result = mockMvc.perform(get(URI))
				.andExpect(status().isOk())
				.andReturn();

		String resultString = result.getResponse().getContentAsString();
		assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
	}

	@Test
	void addReservation_shouldAddReservationToDatabase() throws Exception {
		vaccine = vaccineRepository.save(
				new Vaccine(
						"astrazeneca",
						500L
				)
		);
		reservation.getReservationId().setIdVaccine(vaccine.getVaccineID());

		mockMvc.perform(post(URI)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(jsonPath("$.reservationId.idVaccine").value(vaccine.getVaccineID()))
				.andExpect(jsonPath("$.reservationId.fiscalCode").value(reservation.getReservationId().getFiscalCode()))
				.andExpect(jsonPath("$.clinicName").value(reservation.getClinicName()));

		List<Reservation> result = reservationRepository.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(
						reservation.getReservationId().getFiscalCode(),
						reservation.getReservationId().getIdVaccine()
				);

		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).getReservationId().getFiscalCode()).isEqualTo(reservation.getReservationId().getFiscalCode());
		assertThat(result.get(0).getReservationId().getIdVaccine()).isEqualTo(reservation.getReservationId().getIdVaccine());
	}

	@Test
	void getReservationsByFiscalCode_shouldReturnCorrectReservation() throws Exception {
		// given
		List<Reservation> reservations = List.of(
				reservation,
				reservation2
		);
		ReservationWrapper expected = new ReservationWrapper(List.of(reservation));
		reservationRepository.saveAll(reservations);

		// then
		MvcResult result = mockMvc.perform(get(URI + "/" + fiscalCode)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation)))
				.andExpect(status().isOk())
				.andReturn();

		String resultString = result.getResponse().getContentAsString();
		assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
	}
}