package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
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
class VaccinationCampaignControllerUnitTest {

    @Mock
    private CampagnaVaccinaleService campagnaVaccinaleService;
    private CampagnaVaccinaleController underTest;
    private VaccinationCampaign vaccinationCampaign;

    @BeforeEach
    void setUp() {
        underTest = new CampagnaVaccinaleController(campagnaVaccinaleService);
        vaccinationCampaign = new VaccinationCampaign(
                2L,
                "campagna2",
                Set.of(
                        new Vaccine(
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
        List<VaccinationCampaign> campagnaVaccinali = List.of(
                vaccinationCampaign
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
                .thenReturn(vaccinationCampaign);
        // then
        VaccinationCampaign result =  underTest.addCampagnaVaccinale(vaccinationCampaign);
        verify(campagnaVaccinaleService).addCampagnaVaccinale(vaccinationCampaign);
        assertEquals(vaccinationCampaign, result);
    }
}