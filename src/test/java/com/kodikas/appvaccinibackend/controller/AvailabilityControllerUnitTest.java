package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class AvailabilityControllerUnitTest {

    @Mock
    private AvailabilityService availabilityService;
    private DisponibilitaController disponibilitaController;
    private Availability disponibilita;

    @BeforeEach
    void setUp(){
        disponibilitaController = new DisponibilitaController(availabilityService);
        disponibilita = new Availability(
                "Golosine",1L,"over80",
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
    }
    @Test
    void getDisponibilitaALL() {
    }

    @Test
    void registerNewDisponibilita() {
    }

    @Test
    void getDisponibilita() {
    }
}