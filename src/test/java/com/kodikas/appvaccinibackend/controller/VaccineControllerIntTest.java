package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccinationCampaignService;
import com.kodikas.appvaccinibackend.service.VaccineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VaccineController.class)
class VaccineControllerIntTest {
    private final static String URI = "/vaccines";

    Vaccine vaccine;
    Vaccine expectedVaccine;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VaccineService vaccineService;

    @BeforeEach
    void setUp() {
        vaccine = new Vaccine(
                1L,
                "jansen",
                100L,
                new VaccinationCampaign(
                        "campagna2"
                )
        );
        expectedVaccine = new Vaccine(
                1L,
                "jansen",
                100L,
                new VaccinationCampaign(
                        2L,
                        "campagna2"
                )
        );
    }

    @Test
    void getVaccines_shouldCallService() throws Exception {
        mockMvc.perform(get(URI)).andExpect(status().isOk());
        verify(vaccineService).getVaccines();
    }

    @Test
    void addVaccine_shouldCallServiceAndReturnVaccine() throws Exception {
        // when
        when(vaccineService.addVaccine(any())).thenReturn(expectedVaccine);

        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vaccine)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedVaccine));
    }

    @Test
    void modifyQuantity_shouldCallServiceAndReturnModifiedVaccine() throws Exception {
        long id = 1L;
        long quantity = 50L;

        // given
        expectedVaccine.setQuantity(150L);

        // when
        when(vaccineService.addQuantity(1L, 50L)).thenReturn(expectedVaccine);

        // then
        MvcResult result = mockMvc.perform(put(URI + "/"+ id +"/quantity/"+ quantity))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedVaccine)
        );
    }
}