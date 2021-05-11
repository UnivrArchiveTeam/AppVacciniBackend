package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import com.kodikas.appvaccinibackend.wrapper.CampagnaVaccinaleWrapper;
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

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CampagnaVaccinaleControllerAcceptanceTest {
    private final static String URI = "/campagnevaccinali";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;

    private CampagnaVaccinale campagnaVaccinale;

    @BeforeEach
    void setUp() {
        campagnaVaccinaleRepository.deleteAll();
        this.campagnaVaccinale = new CampagnaVaccinale(
                "campagna4",
                Set.of(
                        new Vaccino(
                                "jansen",
                                100L
                        )
                )
        );
    }

    @Test
    void shouldReturnCampagneVaccinali() throws Exception {
        // given
        campagnaVaccinaleRepository.save(campagnaVaccinale);

        // then
        MvcResult result = mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        CampagnaVaccinaleWrapper resultObject = objectMapper.readValue(response, CampagnaVaccinaleWrapper.class);
        assertThat(resultObject.getCampagneVaccinali()).isNotNull();
        assertThat(resultObject.getCampagneVaccinali().get(0).getVaccini()).isNotNull();
        assertThat(resultObject.getCampagneVaccinali().get(0).getNomeMalattia()).isEqualTo(campagnaVaccinale.getNomeMalattia());
    }

    @Test
    void shouldAddCampagnaVaccinaleToDatabase() throws Exception {
        mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(campagnaVaccinale)))
                .andExpect(status().isOk());

        System.out.println("ALL:");
        List<CampagnaVaccinale> a = campagnaVaccinaleRepository.findAllByNomeMalattia(campagnaVaccinale.getNomeMalattia());

        assertThat(campagnaVaccinaleRepository.existsByNomeMalattia(campagnaVaccinale.getNomeMalattia())).isTrue();
        CampagnaVaccinale found = campagnaVaccinaleRepository
                .findCampagnaVaccinaleByNomeMalattia(
                        campagnaVaccinale.getNomeMalattia()
                ).get();
        assertThat(found).isNotNull();
        assertThat(found.getVaccini()).isNotNull();
    }
}