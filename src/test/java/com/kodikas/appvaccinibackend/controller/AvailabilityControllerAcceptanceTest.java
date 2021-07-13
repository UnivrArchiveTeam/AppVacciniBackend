package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.id.IdAvailability;
import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AvailabilityControllerAcceptanceTest {
	private final static String URI = "/availability";

	private Availability availability;
	private Availability availability2;
	private Vaccine vaccine;
	private long idVaccine;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AvailabilityRepository availabilityRepository;
	@Autowired
	private VaccineRepository vaccineRepository;
	private Vaccine vaccine2;

	@BeforeEach
	void setUp() {
		vaccine = vaccineRepository.save(
				new Vaccine(
						"Astrazeneca",
						500L
				)
		);
		vaccine2 = vaccineRepository.save(
				new Vaccine(
						"Pfizer",
						800L
				)
		);
		idVaccine = vaccine.getVaccineID();
		availability = new Availability(
				"Golosine",
				idVaccine,
				LocalDate.of(2021, 5, 6),
				LocalDate.of(2021, 5, 21),
				LocalTime.of(9, 0),
				LocalTime.of(12, 0),
				vaccine
		);
		availability2 = new Availability(
				"San Martino",
				vaccine2.getVaccineID(),
				LocalDate.of(2021, 9, 1),
				LocalDate.of(2021, 12, 12),
				LocalTime.of(15, 0),
				LocalTime.of(18, 0),
				vaccine2
		);
	}

	@AfterEach
	void tearDown() {
		availabilityRepository.deleteAll();
		vaccineRepository.deleteAll();
	}

	@Test
	void getAvailabilityALL_shouldReturnAllAvailabilitiesInTheDatabase() throws Exception {
		// given
		List<Availability> availabilities = List.of(
				availability,
				availability2
		);
		availabilityRepository.saveAll(availabilities);

		// then
		MvcResult result = mockMvc.perform(get(URI))
				.andExpect(status().isOk())
				.andReturn();

		String resultString = result.getResponse().getContentAsString();
		AvailabilityWrapper wrapper = objectMapper.readValue(resultString, AvailabilityWrapper.class);
		assertThat(wrapper.getAvailability().size()).isEqualTo(2);
		assertThat(
				objectMapper.writeValueAsString(wrapper.getAvailability().get(0))
		).isEqualToIgnoringWhitespace(
				objectMapper.writeValueAsString(availability)
		);
		assertThat(
				objectMapper.writeValueAsString(wrapper.getAvailability().get(1))
		).isEqualToIgnoringWhitespace(
				objectMapper.writeValueAsString(availability2)
		);
	}

	@Test
	void registerNewAvailability_shouldSaveAvailabilityToDatabase() throws Exception {
		// then
		MvcResult result = mockMvc.perform(post(URI)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(availability)))
				.andExpect(status().isOk())
				.andReturn();
		String resultString = result.getResponse().getContentAsString();
		Optional<Availability> optionalAvailability = availabilityRepository.findById(
				new IdAvailability(
						"Golosine",
						1L
				)
		);
		assertThat(optionalAvailability).isPresent();
		assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(optionalAvailability.get()));
	}

//	@Test
//	void getAvailability_shouldReturnCorrectInstanceOfAvailability() throws Exception {
//		// given
//		List<Availability> availabilities = List.of(
//				availability,
//				availability2
//		);
//		availabilityRepository.saveAll(availabilities);
//		AvailabilityWrapper expectedResult = new AvailabilityWrapper(List.of(availability));
//
//		// then
//		MvcResult result = mockMvc.perform(get(URI + "/" + idVaccine))
//				.andExpect(status().isOk())
//				.andReturn();
//
//		String resultString = result.getResponse().getContentAsString();
//		assertThat(resultString).isNotNull();
//		assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResult));
//
//	}
}