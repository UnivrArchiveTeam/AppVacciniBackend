package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.service.CitizenService;
import com.kodikas.appvaccinibackend.wrapper.CitizenWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenControllerUnitTest {
    private CitizenController underTest;
    @Mock private CitizenService citizenService;
    private Citizen citizen;
    private String fiscalCode;

    @BeforeEach
    void setUp() {
        fiscalCode = "MZZMMT61M22D854K";
        underTest = new CitizenController(citizenService);
        citizen = new Citizen(
                fiscalCode,
                237971319838010581L,
                "Mouhameth",
                "Mazza",
                "Gaiarine",
                LocalDate.parse("1961-08-22"),
                "paziente iperteso"
        );
    }

    @Test
    void getCitizens_shouldCallServiceAndReturnCitizenWrapper() {
        // given
        List<Citizen> citizens = List.of(citizen);

        // when
        when(citizenService.getCitizens()).thenReturn(citizens);

        // then
        CitizenWrapper result = underTest.getCitizens();
        verify(citizenService, times(1)).getCitizens();
        assertThat(result.getCitizens()).isEqualTo(citizens);
    }

    @Test
    void getCitizen_shouldCallServiceAndReturnCitizen() {
        // when
        when(citizenService.getCitizen(fiscalCode)).thenReturn(citizen);

        // then
        Citizen result = underTest.getCitizen(fiscalCode);
        verify(citizenService, times(1)).getCitizen(fiscalCode);
        assertThat(result).isEqualTo(citizen);
    }

    @Test
    void modifyRegistered_shouldCallServiceAndReturnCitizen() {
        // given
        citizen.setRegistered(true);

        // when
        when(citizenService.modifyRegistered(fiscalCode)).thenReturn(citizen);

        // then
        Citizen result = underTest.modifyRegistered(fiscalCode);
        verify(citizenService, times(1)).modifyRegistered(fiscalCode);
        assertThat(result).isEqualTo(citizen);
    }
}