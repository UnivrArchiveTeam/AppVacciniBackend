package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccineService;
import com.kodikas.appvaccinibackend.wrapper.VaccineWrapper;
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
    private VaccineService vaccineService;
    private VaccineController underTest;
    private Vaccine vaccine;

    @BeforeEach
    void setUp() {
        underTest = new VaccineController(vaccineService);
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
    void getVaccines_shouldCallServiceAndReturnVaccineWrapper() {
        List<Vaccine> vaccines = List.of(vaccine);

        // when
        when(vaccineService.getVaccines()).thenReturn(vaccines);

        // then
        VaccineWrapper result = underTest.getVaccines();

        verify(vaccineService).getVaccines();
        assertEquals(result.getVaccines(), vaccines);
    }

    @Test
    void addVaccine_shouldCallServiceAndReturnVaccine() {
        // when
        when(vaccineService.addVaccine(any())).thenReturn(vaccine);

        // then
        Vaccine result = underTest.addVaccine(vaccine);
        verify(vaccineService).addVaccine(vaccine);
        assertEquals(vaccine, result);
    }

    @Test
    void modifyQuantity_shouldModifyGivenObjectsCallServiceAndReturnVaccine() {
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
        when(vaccineService.addQuantity(id, 50L)).thenReturn(modifiedVaccine);

        // then
        Vaccine result = underTest.modifyQuantity(id, 50L);

        verify(vaccineService).addQuantity(id, 50L);
        assertEquals(result.getQuantity(), modifiedVaccine.getQuantity());
    }
}