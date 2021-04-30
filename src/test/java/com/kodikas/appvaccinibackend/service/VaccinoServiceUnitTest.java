package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.repository.VaccinoRepository;

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
class VaccinoServiceUnitTest {
    @Mock
    private VaccinoRepository vaccinoRepository;
    private VaccinoService underTest;
    private Vaccino vaccino;

    @BeforeEach
    void setUp() {
        this.underTest = new VaccinoService(vaccinoRepository);
        this.vaccino = new Vaccino(
                8L,
                "jansen",
                100L
        );
    }


    @Test
    void canGetAllVaccini() {
        // given
        List<Vaccino> vaccini = List.of(
                vaccino
        );

        // when
        when(vaccinoRepository.findAll()).thenReturn(vaccini);

        underTest.getVaccini();

        // then
        verify(vaccinoRepository).findAll();
    }

    @Test
    void shouldAddVaccino() {
        // when
        underTest.addVaccino(vaccino);

        // then
        verify(vaccinoRepository).save(vaccino);
    }

    @Test
    void shouldAddQuantity() {
        // when
        Long id = vaccino.getIdVaccino();
        when(vaccinoRepository.existsById(id)).thenReturn(true);
        when(vaccinoRepository.findById(id)).thenReturn(Optional.of(vaccino));

        underTest.aggiungiQuantità(id, 50L);
        // then
        verify(vaccinoRepository).save(any());
        assertThat(vaccino.getQuantità()).isEqualTo(150L);
    }

    @ParameterizedTest
    @ValueSource(longs = {-10, 0, Long.MIN_VALUE})
    void shouldThrowErrorWhenQuantitaLessThanOrEqualZero(Long quantità) {
        // when
        Long id = vaccino.getIdVaccino();

        // then
        assertThatThrownBy(
                () -> underTest.aggiungiQuantità(id, quantità)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("La quantità inserita non è valida");

        verify(vaccinoRepository, never()).save(any());

    }

    @Test
    void shouldThrowErrorWhenIdDoesNotExist() {
        // when
        Long id = vaccino.getIdVaccino();
        when(vaccinoRepository.existsById(id)).thenReturn(false);

        // then
        assertThatThrownBy(
                () -> underTest.aggiungiQuantità(id, 50L)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("Inserire un'id valido");

        verify(vaccinoRepository, never()).save(any());

    }

}