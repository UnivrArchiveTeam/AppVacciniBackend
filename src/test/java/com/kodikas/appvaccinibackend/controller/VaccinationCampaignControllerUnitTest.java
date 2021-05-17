package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.service.VaccinationCampaignService;
import com.kodikas.appvaccinibackend.wrapper.VaccinationCampaignWrapper;
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
    private VaccinationCampaignService vaccinationCampaignService;
    private VaccinationCampaignController underTest;
    private VaccinationCampaign vaccinationCampaign;

    @BeforeEach
    void setUp() {
        underTest = new VaccinationCampaignController(vaccinationCampaignService);
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
    void getVaccinationCampaigns_shouldCallServiceAndReturnListOfVaccinationCampaign() {
        // given
        List<VaccinationCampaign> vaccinationCampaigns = List.of(
                vaccinationCampaign
        );

        // when
        when(vaccinationCampaignService.getVaccinationCampaigns())
                .thenReturn(vaccinationCampaigns);
        VaccinationCampaignWrapper wrapper = underTest.getVaccinationCampaigns();
        verify(vaccinationCampaignService).getVaccinationCampaigns();
        assertEquals(vaccinationCampaigns, wrapper.getVaccinationCampaigns());
    }

    @Test
    void addVaccinationCampaign_shouldCallServiceAndReturnVaccinationCampaign() {
        // when
        when(vaccinationCampaignService.addVaccinationCampaign(any()))
                .thenReturn(vaccinationCampaign);
        // then
        VaccinationCampaign result =  underTest.addVaccinationCampaign(vaccinationCampaign);
        verify(vaccinationCampaignService).addVaccinationCampaign(vaccinationCampaign);
        assertEquals(vaccinationCampaign, result);
    }
}