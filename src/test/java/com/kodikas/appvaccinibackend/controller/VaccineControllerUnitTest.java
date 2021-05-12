package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
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
class VaccineControllerUnitTest {
    @Mock
    private VaccinoService vaccinoService;
    private VaccinoController underTest;
    private Vaccine vaccine;

    @BeforeEach
    void setUp() {
        underTest = new VaccinoController(vaccinoService);
        vaccine = new Vaccine(
                1L,
                "jansen",
                100L,
                new VaccinationCampaign(
                        "campagna2"
                )
        );
    }


    @Test
    void canGetAllVaccini() {
        List<Vaccine> vaccini = List.of(vaccine);

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
        when(vaccinoService.addVaccino(any())).thenReturn(vaccine);

        // then
        Vaccine result = underTest.addVaccino(vaccine);
        verify(vaccinoService).addVaccino(vaccine);
        assertEquals(vaccine, result);
    }

    @Test
    void modifyQuantità() {
        // given
        Vaccine modifiedVaccine = new Vaccine(
                1L,
                "jansen",
                150L,
                new VaccinationCampaign(
                        "campagna2"
                )
        );
        Long id = vaccine.getVaccineID();

        // when
        when(vaccinoService.aggiungiQuantità(id, 50L)).thenReturn(modifiedVaccine);

        // then
        Vaccine result = underTest.modifyQuantità(id, 50L);

        verify(vaccinoService).aggiungiQuantità(id, 50L);
        assertEquals(result.getQuantity(), modifiedVaccine.getQuantity());
    }
}