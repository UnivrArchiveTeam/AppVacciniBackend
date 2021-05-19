package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceUnitTest {
    @Mock
    private VaccineRepository vaccineRepository;
    private VaccineService underTest;
    private Vaccine vaccine;

    @BeforeEach
    void setUp() {
        this.underTest = new VaccineService(vaccineRepository);
        this.vaccine = new Vaccine(
                8L,
                "jansen",
                100L
        );
    }


    @Test
    void getVaccines_shouldReturnListOfVaccines() {
        // given
        List<Vaccine> vaccines = List.of(
                vaccine
        );

        // when
        when(vaccineRepository.findAll()).thenReturn(vaccines);

        underTest.getVaccines();

        // then
        verify(vaccineRepository).findAll();
    }

    @Test
    void addVaccine_shouldCallRepository() {
        // when
        underTest.addVaccine(vaccine);

        // then
        verify(vaccineRepository).save(vaccine);
    }

    @Test
    void addQuantity_shouldCallRepository() {
        // given
        Long id = vaccine.getVaccineID();

        // when
        when(vaccineRepository.findById(id)).thenReturn(Optional.of(vaccine));

        underTest.addQuantity(id, 50L);
        // then
        verify(vaccineRepository).save(any());
        assertThat(vaccine.getQuantity()).isEqualTo(150L);
    }

    @Test
    void addQuantity_shouldThrowErrorWhenQuantityGreaterThanVaccineQuantity() {
        // given
        long quantity = vaccine.getQuantity()+5;

        // when
        when(vaccineRepository.findById(vaccine.getVaccineID())).thenReturn(
                Optional.of(vaccine)
        );

        // then
        assertThatThrownBy(
                () -> underTest.addQuantity(vaccine.getVaccineID(), quantity)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("Insert a Valid quantity");

        verify(vaccineRepository, never()).save(any());

    }

    @Test
    void addQuantity_shouldThrowErrorWhenIdDoesNotExist() {
        // then
        assertThatThrownBy(
                () -> underTest.addQuantity(vaccine.getVaccineID(), 50L)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("Insert a Valid ID");

        verify(vaccineRepository, never()).save(any());

    }

}