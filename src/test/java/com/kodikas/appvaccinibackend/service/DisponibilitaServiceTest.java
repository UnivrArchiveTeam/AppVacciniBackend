package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.controller.DisponibilitaController;
import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.repository.DisponibilitaRepostitory;
import com.kodikas.appvaccinibackend.wrapper.DisponibilitaWrapper;
import org.assertj.core.description.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class DisponibilitaServiceTest {

    @Mock
    private DisponibilitaRepostitory disponibilitaRepostitory;
    private DisponibilitaService disponibilitaService;
    private Disponibilita disponibile;

    @BeforeEach
    void setUp(){
        disponibilitaService = new DisponibilitaService(disponibilitaRepostitory);
        disponibile = new Disponibilita(
                "Golosine",1L,"over80",
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
    }

    @Test
    void getDisponibilita() {
        List<Disponibilita> disponibilita = List.of(disponibile);
        when(disponibilitaRepostitory.findAll()).thenReturn(disponibilita);
        disponibilitaService.getDisponibilita();
        verify(disponibilitaRepostitory).findAll();
    }

    @Test
    void addNewDisponibilita() {

        disponibilitaService.addNewDisponibilita(disponibile);

        verify(disponibilitaRepostitory).save(disponibile);
    }

    @Test
    void getDisponibilitaDateExist() {
        DisponibilitaWrapper disponibilita = new DisponibilitaWrapper(List.of(disponibile));

      assertThat(disponibilitaService.getDisponibilitaDate("over80",1L)).isEqualTo(disponibilita);

      /**
      when(disponibilitaService.getDisponibilitaDate("over80" , 1L )).thenReturn(disponibilita);

        disponibilitaService.getDisponibilitaDate("over80" , 1L);
      verify(disponibilitaRepostitory.findById_IdVaccino(1L));
       **/
    }
}