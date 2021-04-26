package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CampagnaVaccinaleServiceUnitTest {
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;
    private CampagnaVaccinaleService underTest;

    @BeforeEach
    void setUp() {
        this.campagnaVaccinaleRepository = Mockito.mock(CampagnaVaccinaleRepository.class);
        this.underTest = new CampagnaVaccinaleService(campagnaVaccinaleRepository);
    }

    @Test
    void canGetAllCampagneVaccinali() {
        // given
        List<CampagnaVaccinale> campagne = List.of(
                new CampagnaVaccinale(
                        "campagna1"
                ),
                new CampagnaVaccinale(
                        "campagna2"
                )
        );

        // when
        when(campagnaVaccinaleRepository.findAll()).thenReturn(
                campagne
        );

        underTest.getCampagneVaccinali();

        // then
        verify(campagnaVaccinaleRepository).findAll();
    }

    @Test
    void shouldAddCampagnaVaccinale() {
        // given
        CampagnaVaccinale campagnaVaccinale = new CampagnaVaccinale(
                "campagna1"
        );

        // when
        underTest.addCampagnaVaccinale(campagnaVaccinale);

        // then
        verify(campagnaVaccinaleRepository).save(campagnaVaccinale);
    }
}