package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
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
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AvailabilityController.class)
class AvailabilityControllerIntTest {
    private final static String URI = "/availability";

    private Availability availability;
    private long idVaccine;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private AvailabilityService availabilityService;

    @BeforeEach
    void setUp() {
        idVaccine = 1L;
        availability = new Availability(
                "Golosine",
                idVaccine,
                LocalDate.of(2021, 5, 6),
                LocalDate.of(2021, 5,21),
                LocalTime.of(9, 0),
                LocalTime.of(12, 0)
        );
    }

    @Test
    void getAvailabilityALL_shouldCallService() throws Exception {
        // then
        mockMvc.perform(get(URI)).andExpect(status().isOk());
        verify(availabilityService, times(1)).getAllAvailability();
    }

    @Test
    void registerNewAvailability_shouldCallServiceAndReturnVaccine() throws Exception {
        // when
        when(availabilityService.addAvailability(any())).thenReturn(availability);

        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(availability)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(availability));
    }

    @Test
    // TODO Finish
    void getAvailability_shouldReturnAllAvailabilitiesConnectedToASpecificVaccine() throws Exception {
        // when
        when(availabilityService.getAvailabilityByIdVaccine(idVaccine)).thenReturn(List.of(availability));

        // then
        MvcResult result = mockMvc.perform(get(URI+"/"+idVaccine))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isNotNull();
        verify(availabilityService).getAvailabilityByIdVaccine(idVaccine);
    }
}