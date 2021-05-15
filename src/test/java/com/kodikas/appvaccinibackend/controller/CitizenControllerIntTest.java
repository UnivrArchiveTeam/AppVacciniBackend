package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.service.CitizenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CitizenController.class)
class CitizenControllerIntTest {
    private final static String URI = "/citizens";

    private Citizen citizen;
    private String fiscalCode;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private CitizenService citizenService;


    @BeforeEach
    void setUp() {
        fiscalCode = "MZZMMT61M22D854K";
        citizen = new Citizen(
                fiscalCode,
                237971319838010581L,
                "Mouhameth",
                "Mazza",
                "Gaiarine",
                LocalDate.parse("1961-08-22"),
                "paziente iperteso"
        );
    }

    @Test
    void getCitizens_shouldCallService() throws Exception {
        // then
        mockMvc.perform(get(URI)).andExpect(status().isOk());
        verify(citizenService).getCitizens();
    }

    @Test
    void getCitizen_shouldCallServiceAndReturnCitizen() throws Exception {
        // when
        when(citizenService.getCitizen(fiscalCode)).thenReturn(citizen);

        // then
        MvcResult result = mockMvc.perform(get(URI+"/"+fiscalCode))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(citizen)
        );
    }

    @Test
    void modifyRegistered_shouldCallServiceAndReturnModifiedCitizen() throws Exception {
        // when
        citizen.setRegistered(true);
        when(citizenService.modifyRegistered(fiscalCode)).thenReturn(citizen);

        // then
        MvcResult result = mockMvc.perform(put(URI+"/registered/"+fiscalCode))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(citizen)
        );
    }
}