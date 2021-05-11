package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.service.DisponibilitaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DisponibilitaControllerUnitTest {

    @Mock
    private DisponibilitaService disponibilitaService;
    private DisponibilitaController disponibilitaController;
    private Disponibilita disponibilita;

    @BeforeEach
    void setUp(){
        disponibilitaController = new DisponibilitaController(disponibilitaService);
        disponibilita = new Disponibilita(
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