package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.service.VaccineService;
import com.kodikas.appvaccinibackend.wrapper.VaccinationCampaignWrapper;
import com.kodikas.appvaccinibackend.wrapper.VaccineWrapper;
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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerAcceptanceTest {
    private final static String URI = "/vaccines";

    Vaccine vaccine;
    Vaccine expectedVaccine;

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Autowired private VaccineRepository vaccineRepository;

    @AfterEach
    void tearDown() {
        vaccineRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        vaccine = new Vaccine(
                "jansen",
                100L
        );
        expectedVaccine = new Vaccine(
                1L,
                "jansen",
                100L
        );
    }

    @Test
    void getVaccines_shouldRetrieveWrapperOfVaccine() throws Exception {
        // given
        vaccineRepository.save(vaccine);

        // then
        MvcResult result = mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        VaccineWrapper resultObject = objectMapper.readValue(response, VaccineWrapper.class);
        assertThat(resultObject.getVaccines()).isNotNull();
        assertThat(resultObject.getVaccines().size()).isEqualTo(1);
    }

    @Test
    void addVaccine_shouldAddVaccineToDatabase() throws Exception {
        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vaccine)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Vaccine resultObject = objectMapper.readValue(resultString, Vaccine.class);

        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(resultObject.getVaccineID());
        assertThat(optionalVaccine).isPresent();
    }

    @Test
    void modifyQuantity_shouldIncreaseEntityQuantityField() throws Exception {
        // given
        vaccineRepository.save(vaccine);
        long expectedQuantity = 150L;

        // then
        // add 50 doses to vaccine
        MvcResult result = mockMvc.perform(put(URI+ "/"+vaccine.getVaccineID()+"/quantity/50"))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Vaccine resultObject = objectMapper.readValue(resultString, Vaccine.class);

        assertThat(resultObject.getQuantity()).isEqualTo(expectedQuantity);
    }
}