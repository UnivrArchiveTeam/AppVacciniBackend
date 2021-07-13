package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccinationCampaignWrapper;
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
    private final static String URI = "/vaccinationCampaign";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VaccinationCampaignRepository vaccinationCampaignRepository;
    @Autowired
    private VaccineRepository vaccineRepository;

    private VaccinationCampaign vaccinationCampaign;

    @BeforeEach
    void setUp() {
        vaccineRepository.deleteAll();
        vaccinationCampaignRepository.deleteAll();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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
    void getVaccinationCampaigns_shouldRetrieveListOfVaccinationCampaignFromDatabase() throws Exception {
        // given
        vaccinationCampaignRepository.save(vaccinationCampaign);

        // then
        MvcResult result = mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        VaccinationCampaignWrapper resultObject = objectMapper.readValue(response, VaccinationCampaignWrapper.class);
        assertThat(resultObject.getVaccinationCampaigns()).isNotNull();
        assertThat(resultObject.getVaccinationCampaigns().get(0).getVaccines()).isNotNull();
        assertThat(resultObject.getVaccinationCampaigns().get(0).getDiseaseName()).isEqualTo(vaccinationCampaign.getDiseaseName());
    }

    @Test
    void addVaccinationCampaign_shouldAddVaccinationCampaignToDatabase() throws Exception {

        mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vaccinationCampaign)))
                .andExpect(status().isOk());

        assertThat(vaccinationCampaignRepository.existsByDiseaseName(vaccinationCampaign.getDiseaseName())).isTrue();
        VaccinationCampaign found = vaccinationCampaignRepository
                .findVaccinationCampaignByDiseaseName(
                        vaccinationCampaign.getDiseaseName()
                ).get();
        assertThat(found).isNotNull();
        assertThat(found.getVaccines()).isNotNull();
    }
}