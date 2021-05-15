package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccinationCampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VaccinationCampaignController.class)
class VaccinationCampaignControllerIntTest {
    private final static String URI = "/vaccinationCampaign";

    VaccinationCampaign vaccinationCampaign;
    VaccinationCampaign expectedVaccinationCampaign;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VaccinationCampaignService vaccinationCampaignService;


    @BeforeEach
    void setUp() {
        this.vaccinationCampaign = new VaccinationCampaign(
                "campagna2",
                Set.of(
                        new Vaccine(
                                "jansen",
                                100L
                        )
                )
        );

        this.expectedVaccinationCampaign = new VaccinationCampaign(
                2L,
                "campagna2",
                Set.of(
                        new Vaccine(
                                1L,
                                "jansen",
                                100L
                        )
                )
        );
    }


    @Test
    void getVaccinationCampaigns_shouldCallService() throws Exception {
        mockMvc.perform(get(URI)).andExpect(status().isOk());
        verify(vaccinationCampaignService).getVaccinationCampaigns();
    }

    @Test
    void addVaccinationCampaign_shouldReturnVaccinationCampaign() throws Exception {
        // when
        when(vaccinationCampaignService.addVaccinationCampaign(any())).thenReturn(expectedVaccinationCampaign);

        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vaccinationCampaign)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedVaccinationCampaign)
        );
    }
}