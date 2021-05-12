package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import com.kodikas.appvaccinibackend.wrapper.CampagnaVaccinaleWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VaccinationCampaignControllerAcceptanceTest {
    private final static String URI = "/campagnevaccinali";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;

    private VaccinationCampaign vaccinationCampaign;

    @BeforeEach
    void setUp() {
        campagnaVaccinaleRepository.deleteAll();
        this.vaccinationCampaign = new VaccinationCampaign(
                "campagna4",
                Set.of(
                        new Vaccine(
                                "jansen",
                                100L
                        )
                )
        );
    }

    @Test
    void getCampagneVaccinali_shouldReturnCampagneVaccinali() throws Exception {
        // given
        campagnaVaccinaleRepository.save(vaccinationCampaign);

        // then
        MvcResult result = mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        CampagnaVaccinaleWrapper resultObject = objectMapper.readValue(response, CampagnaVaccinaleWrapper.class);
        assertThat(resultObject.getCampagneVaccinali()).isNotNull();
        assertThat(resultObject.getCampagneVaccinali().get(0).getVaccines()).isNotNull();
        assertThat(resultObject.getCampagneVaccinali().get(0).getDiseaseName()).isEqualTo(vaccinationCampaign.getDiseaseName());
    }

    @Test
    void shouldAddCampagnaVaccinaleToDatabase() throws Exception {
        mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vaccinationCampaign)))
                .andExpect(status().isOk());

        assertThat(campagnaVaccinaleRepository.existsByNomeMalattia(vaccinationCampaign.getDiseaseName())).isTrue();
        VaccinationCampaign found = campagnaVaccinaleRepository
                .findCampagnaVaccinaleByNomeMalattia(
                        vaccinationCampaign.getDiseaseName()
                ).get();
        assertThat(found).isNotNull();
        assertThat(found.getVaccines()).isNotNull();
    }
}