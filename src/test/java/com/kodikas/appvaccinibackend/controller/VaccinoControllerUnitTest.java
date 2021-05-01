package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.service.VaccinoService;
import com.kodikas.appvaccinibackend.wrapper.VaccinoWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VaccinoControllerUnitTest {
    @Mock
    private VaccinoService vaccinoService;
    private VaccinoController underTest;
    private Vaccino vaccino;

    @BeforeEach
    void setUp() {
        underTest = new VaccinoController(vaccinoService);
        vaccino = new Vaccino(
                1L,
                "jansen",
                100L,
                new CampagnaVaccinale(
                        "campagna2"
                )
        );
    }


    @Test
    void canGetAllVaccini() {
        List<Vaccino> vaccini = List.of(vaccino);

        // when
        when(vaccinoService.getVaccini()).thenReturn(vaccini);

        // then
        VaccinoWrapper result = underTest.getVaccini();

        verify(vaccinoService).getVaccini();
        assertEquals(result.getVaccini(), vaccini);
    }

    @Test
    void shouldAddVaccino() {
        // when
        when(vaccinoService.addVaccino(any())).thenReturn(vaccino);

        // then
        Vaccino result = underTest.addVaccino(vaccino);
        verify(vaccinoService).addVaccino(vaccino);
        assertEquals(vaccino, result);
    }

    @Test
    void modifyQuantità() {
        // given
        Vaccino modifiedVaccino = new Vaccino(
                1L,
                "jansen",
                150L,
                new CampagnaVaccinale(
                        "campagna2"
                )
        );
        Long id = vaccino.getIdVaccino();

        // when
        when(vaccinoService.aggiungiQuantità(id, 50L)).thenReturn(modifiedVaccino);

        // then
        Vaccino result = underTest.modifyQuantità(id, 50L);

        verify(vaccinoService).aggiungiQuantità(id, 50L);
        assertEquals(result.getQuantità(), modifiedVaccino.getQuantità());
    }
}