package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.service.CampagnaVaccinaleService;
import com.kodikas.appvaccinibackend.wrapper.CampagnaVaccinaleWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CampagnaVaccinaleControllerUnitTest {

    @Mock
    private CampagnaVaccinaleService campagnaVaccinaleService;
    private CampagnaVaccinaleController underTest;
    private CampagnaVaccinale campagnaVaccinale;

    @BeforeEach
    void setUp() {
        underTest = new CampagnaVaccinaleController(campagnaVaccinaleService);
        campagnaVaccinale = new CampagnaVaccinale(
                2L,
                "campagna2",
                Set.of(
                        new Vaccino(
                                1L,
                                "jansen",
                                100L
                        )
                )
        );
    }

    @Test
    void canGetAllCampagneVaccinali() {
        // given
        List<CampagnaVaccinale> campagnaVaccinali = List.of(
                campagnaVaccinale
        );

        // when
        when(campagnaVaccinaleService.getCampagneVaccinali())
                .thenReturn(campagnaVaccinali);
        CampagnaVaccinaleWrapper wrapper = underTest.getCampagneVaccinali();
        verify(campagnaVaccinaleService).getCampagneVaccinali();
        assertEquals(campagnaVaccinali, wrapper.getCampagneVaccinali());
    }

    @Test
    void shouldAddCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleService.addCampagnaVaccinale(any()))
                .thenReturn(campagnaVaccinale);
        // then
        CampagnaVaccinale result =  underTest.addCampagnaVaccinale(campagnaVaccinale);
        verify(campagnaVaccinaleService).addCampagnaVaccinale(campagnaVaccinale);
        assertEquals(campagnaVaccinale, result);
    }
}