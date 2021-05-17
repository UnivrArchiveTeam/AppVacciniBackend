package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CitizenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceUnitTest {
    private static final String ERROR_MESSAGE = "Insert a Valid FiscalCode";
    @Mock private CitizenRepository citizenRepository;
    private CitizenService underTest;
    private Citizen citizen;
    private String fiscalCode;

    @BeforeEach
    void setUp() {
        fiscalCode = "MZZMMT61M22D854K";
        underTest = new CitizenService(citizenRepository);
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
    void getCitizens_shouldCallRepositoryAndReturnListOfCitizens() {
        // given
        List<Citizen> citizens = List.of(citizen);

        // when
        when(citizenRepository.findAll()).thenReturn(citizens);

        List<Citizen> result = underTest.getCitizens();
        verify(citizenRepository, times(1)).findAll();
        assertThat(result).isEqualTo(citizens);

    }

    @Test
    void getCitizen_nullFiscalCode_shouldThrowException() {
        assertThatThrownBy(
                () -> underTest.getCitizen(null)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE);

        verify(citizenRepository, never()).findById(any());
    }

    @Test
    void getCitizen_emptyOptional_shouldThrowException() {
        // given
        Optional<Citizen> optionalCitizen = Optional.empty();

        // when
        when(citizenRepository.findById(fiscalCode)).thenReturn(optionalCitizen);

        // then
        assertThatThrownBy(
                () -> underTest.getCitizen(fiscalCode)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE);
    }

    @Test
    void getCitizen_shouldReturnCitizen() {
        // given
        Optional<Citizen> optionalCitizen = Optional.of(citizen);

        // when
        when(citizenRepository.findById(fiscalCode)).thenReturn(optionalCitizen);

        // then
        Citizen result = underTest.getCitizen(fiscalCode);
        verify(citizenRepository, times(1)).findById(fiscalCode);
        assertThat(result).isEqualTo(citizen);
    }

    @Test
    void modifyRegistered_nullFiscalCode_shouldThrowException() {
        assertThatThrownBy(
                () -> underTest.modifyRegistered(null)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE);

        verify(citizenRepository, never()).findById(any());
        verify(citizenRepository, never()).save(any());
    }

    @Test
    void modifyRegistered_emptyOptional_shouldThrowException() {
        // given
        Optional<Citizen> optionalCitizen = Optional.empty();

        // when
        when(citizenRepository.findById(fiscalCode)).thenReturn(optionalCitizen);

        // then
        assertThatThrownBy(
                () -> underTest.modifyRegistered(fiscalCode)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE);

        verify(citizenRepository, never()).save(any());
    }

    @Test
    void modifyRegistered_shouldReturnCitizenWithRegisteredFieldTrue() {
        // given
        Optional<Citizen> optionalCitizen = Optional.of(citizen);
        citizen.setRegistered(true);

        // when
        when(citizenRepository.findById(fiscalCode)).thenReturn(optionalCitizen);
        when(citizenRepository.save(citizen)).thenReturn(citizen);

        // then
        Citizen result = underTest.modifyRegistered(fiscalCode);

        verify(citizenRepository, times(1)).save(citizen);
        assertThat(result.isRegistered()).isTrue();
    }
}