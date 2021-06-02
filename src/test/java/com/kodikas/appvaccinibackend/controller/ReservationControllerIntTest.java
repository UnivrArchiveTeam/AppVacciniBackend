package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.service.ReservationService;
import com.kodikas.appvaccinibackend.wrapper.ReservationWrapper;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ReservationController.class)
class ReservationControllerIntTest {
    private final static String URI = "/reservations";

    private Reservation reservation;
    private Reservation reservation2;
    private String fiscalCode;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        fiscalCode = "MZZMMT61M22D854K";
        reservation = new Reservation(
                1L,
                fiscalCode,
                "Golosine",
                LocalDate.of(2021, 6,21),
                LocalTime.of(10, 0)

        );
        reservation2 = new Reservation(
                2L,
                "NNCFNC59C67E709B",
                "San Martino",
                LocalDate.of(2021, 7,6),
                LocalTime.of(15, 0)
        );
    }

    @Test
    void getAllReservations_shouldReturnReservationWrapper() throws Exception {
        // given
        List<Reservation> reservations = List.of(
                reservation,
                reservation2
        );
        ReservationWrapper expectedReservation = new ReservationWrapper(reservations);

        // when
        when(reservationService.getAllReservations()).thenReturn(reservations);

        // then
        MvcResult result = mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedReservation));
    }

    @Test
    void addReservation_shouldCallService() throws Exception {
        // when
        when(reservationService.addReservation(any())).thenReturn(reservation);

        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(reservation));
    }

    @Test
    void getReservationsByFiscalCode_shouldReturnCorrectReservation() throws Exception {
        // given
        ReservationWrapper reservationWrapper = new ReservationWrapper(
                List.of(reservation)
        );

        // when
        when(reservationService.getReservation(fiscalCode)).thenReturn(List.of(reservation));

        // then
        MvcResult result = mockMvc.perform(get(URI+"/"+fiscalCode)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(reservationWrapper));
    }
}